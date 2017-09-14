package com.dev.kbstudios.kbstudiosattendance;

/**
 * Created by kbstudios on 14/9/17.
 */

public class Classroom {

    private String className;
    private String Key;

    public Classroom(){

    }

    public Classroom(String name){
        this.setClassName(name);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
