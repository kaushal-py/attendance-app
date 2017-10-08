package com.dev.kbstudios.kbstudiosattendance;

/**
 * Created by kbstudios on 6/9/17.
 */

public class Attendee {

    private int rollNo;
    private String fullName;
    private int status;
    private String key;

    public Attendee(){
        // Data snapshot needs a constructor with no arguments
    };

    public Attendee(int rollNo, String fullName, int status){
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.status = status;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public void toggleStatus() {
        this.status = (this.status + 1)%2 ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
