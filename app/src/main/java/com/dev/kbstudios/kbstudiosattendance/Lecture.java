package com.dev.kbstudios.kbstudiosattendance;

/**
 * Created by kbstudios on 3/10/17.
 */

public class Lecture {

    private String classKey;
    private int hourOfDay;
    private int minute;
    private int year;
    private int month;
    private int day;

    public Lecture(){
        // Data snapshot needs a constructor with no arguments
    }

    public Lecture(String classKey, int hourOfDay, int minute, int year, int month, int day){
        this.setClassKey(classKey);
        this.setHourOfDay(hourOfDay);
        this.setMinute(minute);
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
