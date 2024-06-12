-----------------------------
--- CREATE DB AND TABLES ---
-----------------------------
USE master
GO

-- Delete if already existing
IF EXISTS (SELECT 1 FROM sys.databases WHERE name = 'dbRoomBooking')
DROP DATABASE dbRoomBooking;
GO

-- Create the database
CREATE DATABASE dbRoomBooking;
GO

-- Use the newly created database
USE dbRoomBooking;
GO

-----------------------------
----- STORED PROCEDURES -----
-----------------------------


-- Procedure for getting the booked times within a week, for a user
CREATE PROCEDURE sp_GetPlannedBookingsDefault @Date date, @UserID int
AS
BEGIN
    SELECT *, DATENAME(WEEKDAY, fldDate) AS fldWeekday 
    FROM tblBooking 
    WHERE fldDate BETWEEN @Date AND DATEADD(day, 6, @Date) AND fldUserID = @UserID
    ORDER BY fldDate, fldTimeStart
END;
GO

-- Procedure for getting the booked times within a custom interval, for a user
CREATE PROCEDURE sp_GetPlannedBookingsCustom @DateStart date, @DateEnd date, @UserID int
AS
BEGIN
    SELECT *, DATENAME(WEEKDAY, fldDate) AS fldWeekday 
    FROM tblBooking 
    WHERE fldDate BETWEEN @DateStart AND @DateEnd AND fldUserID = @UserID
    ORDER BY fldDate, fldTimeStart
END;
GO
-- Procedure for getting the available times on a specific date
CREATE PROCEDURE sp_EditBookingGetAvailableTimes @Date date, @RoomID int, @BookingID int
AS
BEGIN
    -- Temporary table to store booking hours
    CREATE TABLE #workday (
        start_time TIME,
        end_time TIME
    );

    -- Insert the workday hours
    INSERT INTO #workday (start_time, end_time)
    VALUES ('08:00:00', '16:00:00');

    -- Temporary table to store busy times
    CREATE TABLE #busy_times (
        start_time TIME,
        end_time TIME
    );

    -- Insert booked times
    INSERT INTO #busy_times (start_time, end_time)
    SELECT fldTimeStart, fldTimeEnd 
    FROM tblBooking 
    WHERE fldDate = @Date AND fldRoomID = @RoomID AND NOT fldBookingID = @BookingID;

    -- Temporary table to store available times
    CREATE TABLE #available_times (
        start_time TIME,
        end_time TIME
    );

    -- Calculate available times before the first booking
    INSERT INTO #available_times (start_time, end_time)
    SELECT 
        '08:00:00' AS start_time, 
        DATEADD(SECOND, -1, MIN(start_time)) AS end_time
    FROM #busy_times
    HAVING MIN(start_time) > '08:00:00';

    -- Calculate available times between bookings and after the last booking
    INSERT INTO #available_times (start_time, end_time)
    SELECT 
        DATEADD(SECOND, 1, bt1.end_time) AS start_time, 
        COALESCE(MIN(bt2.start_time), '16:00:00') AS end_time
    FROM #busy_times bt1
    LEFT JOIN #busy_times bt2 ON bt2.start_time > bt1.end_time
    GROUP BY bt1.end_time
    HAVING DATEADD(SECOND, 1, bt1.end_time) < COALESCE(MIN(bt2.start_time), '16:00:00');

    -- Select available times within workday bounds
    SELECT start_time, end_time
    FROM #available_times
    WHERE start_time BETWEEN '08:00:00' AND '16:00:00'
      AND end_time BETWEEN '08:00:00' AND '16:00:00'
    ORDER BY start_time;

    -- Clean up temporary tables
    DROP TABLE #workday;
    DROP TABLE #busy_times;
    DROP TABLE #available_times;
END;
GO

-- Procedure for getting available rooms on a specific date within a specific time range with specific equipment and size
CREATE PROCEDURE sp_GetAvailableRoomsFilter
    @Date DATE,
    @StartTime TIME = NULL,
    @EndTime TIME = NULL,
    @MinRoomSize INT,
    @RequiresEquip1 BIT,
    @RequiresEquip2 BIT,
    @RequiresEquip3 BIT,
    @RequiresEquip4 BIT,
    @RoomID INT = NULL -- Optional parameter to filter by specific room
AS
BEGIN
    -- Set default times if not specified
    IF @StartTime IS NULL
        SET @StartTime = '08:00:00';
    IF @EndTime IS NULL
        SET @EndTime = '16:00:00';
    IF @RoomID = 0
        SET @RoomID = NULL;

    -- Temporary table to store all rooms
    CREATE TABLE #all_rooms (
        fldRoomID INT
    );

    -- Insert all room IDs
    INSERT INTO #all_rooms (fldRoomID)
    SELECT DISTINCT fldRoomID FROM tblRoom;

    -- Temporary table to store busy times
    CREATE TABLE #busy_times (
        fldRoomID INT,
        start_time TIME,
        end_time TIME
    );

    -- Insert busy times
    INSERT INTO #busy_times (fldRoomID, start_time, end_time)
    SELECT fldRoomID, fldTimeStart, fldTimeEnd
    FROM tblBooking
    WHERE fldDate = @Date;

    -- Temporary table to store available times
    CREATE TABLE #available_times (
        fldRoomID INT,
        start_time TIME,
        end_time TIME
    );

    -- Calculate available times before the first booking of each room
    INSERT INTO #available_times (fldRoomID, start_time, end_time)
    SELECT 
        ar.fldRoomID,
        @StartTime AS start_time, 
        DATEADD(SECOND, -1, bt.min_start_time) AS end_time
    FROM #all_rooms ar
    LEFT JOIN (
        SELECT fldRoomID, MIN(start_time) AS min_start_time
        FROM #busy_times
        GROUP BY fldRoomID
    ) bt ON ar.fldRoomID = bt.fldRoomID
    WHERE bt.min_start_time > @StartTime;

    -- Calculate available times between bookings
    INSERT INTO #available_times (fldRoomID, start_time, end_time)
    SELECT 
        bt1.fldRoomID,
        DATEADD(SECOND, 1, bt1.end_time) AS start_time, 
        DATEADD(SECOND, -1, bt2.start_time) AS end_time
    FROM #busy_times bt1
    JOIN #busy_times bt2 ON bt1.fldRoomID = bt2.fldRoomID
    WHERE bt1.end_time < bt2.start_time;

    -- Calculate available times after the last booking of each room
    INSERT INTO #available_times (fldRoomID, start_time, end_time)
    SELECT 
        bt.fldRoomID,
        DATEADD(SECOND, 1, bt.end_time) AS start_time, 
        @EndTime AS end_time
    FROM #busy_times bt
    WHERE bt.end_time < @EndTime
    AND NOT EXISTS (
        SELECT 1 
        FROM #busy_times bt2 
        WHERE bt2.fldRoomID = bt.fldRoomID 
        AND bt2.start_time > bt.end_time
    );

    -- Add times for rooms without any bookings
    INSERT INTO #available_times (fldRoomID, start_time, end_time)
    SELECT 
        ar.fldRoomID, 
        @StartTime, 
        @EndTime 
    FROM #all_rooms ar
    WHERE ar.fldRoomID NOT IN (SELECT fldRoomID FROM #busy_times);

    -- Select available rooms that are not busy during the specified time and meet the equipment and size criteria
    SELECT r.*, at.start_time, at.end_time
    FROM #available_times at
    JOIN tblRoom r ON at.fldRoomID = r.fldRoomID
    LEFT JOIN tblRoomEquipment re1 ON r.fldRoomID = re1.fldRoomID AND re1.fldEquipmentID = 1
    LEFT JOIN tblRoomEquipment re2 ON r.fldRoomID = re2.fldRoomID AND re2.fldEquipmentID = 2
    LEFT JOIN tblRoomEquipment re3 ON r.fldRoomID = re3.fldRoomID AND re3.fldEquipmentID = 3
    LEFT JOIN tblRoomEquipment re4 ON r.fldRoomID = re4.fldRoomID AND re4.fldEquipmentID = 4
    WHERE r.fldRoomSize >= @MinRoomSize
      AND (@RequiresEquip1 = 0 OR re1.fldRoomID IS NOT NULL)
      AND (@RequiresEquip2 = 0 OR re2.fldRoomID IS NOT NULL)
      AND (@RequiresEquip3 = 0 OR re3.fldRoomID IS NOT NULL)
      AND (@RequiresEquip4 = 0 OR re4.fldRoomID IS NOT NULL)
      AND (@RoomID IS NULL OR r.fldRoomID = @RoomID)
      AND at.start_time < @EndTime AND at.end_time > @StartTime
    ORDER BY r.fldRoomID, at.start_time;

    -- Clean up temporary tables
    DROP TABLE #all_rooms;
    DROP TABLE #busy_times;
    DROP TABLE #available_times;
END;
GO

-- Create table for rooms
CREATE TABLE tblRoom (
    fldRoomID INT IDENTITY(1,1) PRIMARY KEY,
    fldRoomName VARCHAR(255) NOT NULL,
    fldRoomSize INT
);
GO

-- Create table for user roles
CREATE TABLE tblUserRoles (
    fldUserRoleID INT IDENTITY(1,1) PRIMARY KEY,
    fldRoles VARCHAR(255) NOT NULL
);

-- Create table for users
CREATE TABLE tblUser (
    fldUserID INT IDENTITY(1,1) PRIMARY KEY,
    fldUsername VARCHAR(255) NOT NULL,
    fldPassword VARCHAR(255) NOT NULL,
    fldEmail VARCHAR(255) NOT NULL,
    fldTlf VARCHAR(50),
    fldUserRolesID INT,
    FOREIGN KEY (fldUserRolesID) REFERENCES tblUserRoles(fldUserRoleID)
);

-- Create table for fault statuses
CREATE TABLE tblFaultStatus (
    fldFaultStatusID INT IDENTITY(1,1) PRIMARY KEY,
    fldFaultStatusText VARCHAR(255) NOT NULL
);

-- Create table for faults
CREATE TABLE tblFault (
    fldFaultID INT IDENTITY(1,1) PRIMARY KEY,
    fldFaultTxt TEXT NOT NULL,
    fldRoomID INT,
    fldFaultStatusID INT,
    FOREIGN KEY (fldRoomID) REFERENCES tblRoom(fldRoomID),
    FOREIGN KEY (fldFaultStatusID) REFERENCES tblFaultStatus(fldFaultStatusID)
);

-- Create table for bookings
CREATE TABLE tblBooking (
    fldBookId INT IDENTITY(1,1) PRIMARY KEY,
    fldTitle VARCHAR(255) NOT NULL,
    fldDate DATE NOT NULL,
    fldTimeStart TIME NOT NULL,
    fldTimeEnd TIME NOT NULL,
    fldCatering VARCHAR(255),
    fldRoomID INT,
    fldUserID INT,
    FOREIGN KEY (fldRoomID) REFERENCES tblRoom(fldRoomID),
    FOREIGN KEY (fldUserID) REFERENCES tblUser(fldUserID)
);

-- Create table for equipment
CREATE TABLE tblEquipment (
    fldEquipmentID INT IDENTITY(1,1) PRIMARY KEY,
    fldEquipment VARCHAR(255) NOT NULL
);

-- Create table for room equipment (junction table)
CREATE TABLE tblRoomEquipment (
    fldREID INT IDENTITY(1,1) PRIMARY KEY,
    fldAntal INT NOT NULL,
    fldEquipmentID INT,
    fldRoomID INT,
    FOREIGN KEY (fldEquipmentID) REFERENCES tblEquipment(fldEquipmentID),
    FOREIGN KEY (fldRoomID) REFERENCES tblRoom(fldRoomID)
);
GO

-----------------------------
----- INSERT DUMMYDATA  -----
-----------------------------

-- Insert data into UserRoles
INSERT INTO [dbo].[tblUserRoles] ([fldRoles])
VALUES
('Admin'),
('Guest'),
('Employee'),
('Maintenance staff'),
('Student');

-- Insert data into tblUser
INSERT INTO [dbo].[tblUser] ([fldUsername], [fldPassword], [fldEmail], [fldTlf], [fldUserRolesID])
VALUES
('admin', '123', 'admin@gmail.com', '42779382', 1),
('gæstebruger1', 'gæst123', 'gæst@gmail.com', '42554598', 2),
('LarsLarsen', 'Lars123', 'Lars@gmail.com', '10897659', 3),
('pedel', 'pedel123', 'pedel@hotmail.com','50303010', 4),
('HansHansen', 'Hans321', 'HansHansen1@gmail.com', '42778291', 5),
('JensJensen', 'Jens123', 'JensJensen20@gmail.com', '45679181', 5),
('NielsNielsen','Niels129', 'NielsNielsen13@hotmail.com','79481313',5);

-- Insert data into tblRoom
INSERT INTO [dbo].[tblRoom] ([fldRoomName], [fldRoomSize])
VALUES
('Lokale 306', 30),
('Lokale 402', 40),
('Lokale 330', 50),
('Lokale 301', 80);

-- Insert data into tblEquipment
INSERT INTO [dbo].[tblEquipment] ([fldEquipment])
VALUES
('Projektor'),
('Whiteboard'),
('Højtaler'),
('Strømforsyning');

-- Insert data into tblRoomEquipment
INSERT INTO [dbo].[tblRoomEquipment] ([fldAntal], [fldEquipmentID], [fldRoomID])
VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 1),
(1, 1, 2),
(1, 1, 3);

INSERT INTO [dbo].[tblFaultStatus] ([fldFaultStatusText])
VALUES
('igangværende'),
('udført'),
('inaktiv');

-- Insert data into tblFault
INSERT INTO [dbo].[tblFault] ([fldFaultTxt], [fldRoomID], [fldFaultStatusID])
VALUES
('Projektor fejl', 1, 1),
('Fejl med højtalere', 2, 1),
('Fejl med sænkebord', 3, 1),
('Fejl med radiator', 2, 2),
('Whiteboard mangler tusch/hvisker', 2, 3);

-- Insert data into tblBooking
INSERT INTO [dbo].[tblBooking] ([fldTitle], [fldDate], [fldTimeStart], [fldTimeEnd], [fldCatering], [fldRoomID], [fldUserID])
VALUES
('Salgsmøde', '2024-05-29', '11:00:00', '12:00:00', 1, 4, 2),
('DMU23', '2024-05-29', '08:00:00', '10:00:00', 0, 1, 3),
('Skakklub møde', '2024-05-29', '11:00:00', '12:00:00', 1, 1, 1),
('DMU22', '2024-05-29', '14:00:00', '15:00:00', 0, 1, 3),
('Teambulding Session', '2024-05-29', '11:00:00', '12:00:00', 1, 4, 2);

GO