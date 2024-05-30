package com.example.roombookingsystem.foundation;

public class ObservableRoomTest {

    private String lokaleNavn;
    private String tid;
    private String fejl;
    private String handle;

    public ObservableRoomTest(String lokaleNavn, String tid, String fejl, String handle) {
        this.lokaleNavn = lokaleNavn;
        this.tid = tid;
        this.fejl = fejl;
        this.handle = handle;
    }
    public String getLokaleNavn() {
        return lokaleNavn;
    }

    public String getTid() {
        return tid;
    }

    public String getFejl() {
        return fejl;
    }
    public String getHandle() {
        return handle;
    }
}
