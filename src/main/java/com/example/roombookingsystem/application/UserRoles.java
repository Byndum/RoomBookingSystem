package com.example.roombookingsystem.application;

import java.util.HashMap;

public class UserRoles {
    public enum ROLESLIST
    {
        STUDENT,
        EMPLOYEE,
        MAINTENANCESTAFF,
        ADMIN;
    }

    private static HashMap<Integer, ROLESLIST> idToList = new HashMap<Integer, ROLESLIST>(){{
        put(1, ROLESLIST.ADMIN);
        put(3, ROLESLIST.EMPLOYEE);
        put(4, ROLESLIST.MAINTENANCESTAFF);
        put(5, ROLESLIST.STUDENT);
    }};

    public static ROLESLIST getRole(int roleID) {
        return idToList.get(roleID);
    }
}
