package com.dev.kbstudios.kbstudiosattendance;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AttendanceMenu extends AppCompatActivity {

    private int hourOfDay;
    private int minute;
    private int year;
    private int month;
    private int day;

    private DatabaseReference mDatabaseStudents;
    private DatabaseReference mDatabaseAttendees;
    private DatabaseReference mDatabaseAttendeesStudents;

    String lectureKey;
    String classKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sessionUser = getApplicationContext()
                .getSharedPreferences("kbstudiosattendance.userdata", Context.MODE_PRIVATE);
        String firebaseEmail = sessionUser.getString("user", null);

        mDatabaseAttendees = FirebaseDatabase.getInstance().getReference().child(firebaseEmail).child("attendees");

        classKey = getIntent().getStringExtra("classKey");
        mDatabaseStudents = FirebaseDatabase.getInstance().getReference().child(firebaseEmail).child("class").child(classKey).child("students");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTakeAttendance);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment2 = new DatePickerFragment();
                newFragment2.show(getFragmentManager(), "datePicker");

            }
        });
    }

    public void gotoTime(){
        DialogFragment newFragment1 = new TimePickerFragment();
        newFragment1.show(getFragmentManager(), "timePicker");
    }

    public void gotoAttendance(){
        mDatabaseStudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lecture lecture = new Lecture(classKey,
                        getHourOfDay(),
                        getMinute(),
                        getYear(),
                        getMonth(),
                        getDay());
                lectureKey = mDatabaseAttendees.push().getKey();
                mDatabaseAttendees.child(lectureKey).setValue(lecture);
                mDatabaseAttendeesStudents = mDatabaseAttendees.child(lectureKey).child("students");
                int i = 1;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Student student = snapshot.getValue(Student.class);
                    Attendee attendee = new Attendee(i, student.getFullName(), 0);
                    mDatabaseAttendeesStudents.push().setValue(attendee);
                    i++;
                }

                Intent intent = new Intent(AttendanceMenu.this, MainActivity.class);
                intent.putExtra("lectureKey", lectureKey);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
