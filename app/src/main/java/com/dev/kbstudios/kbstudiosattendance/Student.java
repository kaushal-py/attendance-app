package com.dev.kbstudios.kbstudiosattendance;

/**
 * Created by kbstudios on 12/9/17.
 */

public class Student {

    private String fullName;

    public Student(){

    }

    public Student(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
