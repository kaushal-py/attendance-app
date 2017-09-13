package com.dev.kbstudios.kbstudiosattendance;

/**
 * Created by kbstudios on 12/9/17.
 */

public class Teacher {

    private String email;
    private String fullName;

    public Teacher(){

    }

    public Teacher(String email, String fullName, int role){
        this.email = email;
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
