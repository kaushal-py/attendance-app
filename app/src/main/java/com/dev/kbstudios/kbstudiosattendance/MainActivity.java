package com.dev.kbstudios.kbstudiosattendance;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Attendee> studentAttendees = new ArrayList<>();

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String lectureKey = getIntent().getStringExtra("lectureKey");

        SharedPreferences sessionUser = getApplicationContext()
                .getSharedPreferences("kbstudiosattendance.userdata", Context.MODE_PRIVATE);
        String firebaseEmail = sessionUser.getString("user", null);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(firebaseEmail).child("attendees").child(lectureKey).child("students");

        final ArrayAdapter<Attendee> adapter = new AttendeeArrayAdapter(this, 0, studentAttendees);
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Attendee attendee = dataSnapshot.getValue(Attendee.class);
                attendee.setKey(dataSnapshot.getKey());
                adapter.add(attendee);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        //  mDatabase.addValueEventListener(attendeeListener);


//        for (int i = 1; i<=78; i++){
//            Attendee attendee = new Attendee(i, "Kaushal", 0);
//            studentAttendees.add(attendee);
//            // mDatabase.push().setValue(attendee);
//        }

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Attendee attendee = studentAttendees.get(position);

                attendee.toggleStatus();

                mDatabase.child(attendee.getKey()).setValue(attendee);

                adapter.notifyDataSetChanged();

            }
        };
        //set the listener to the list view
        gridView.setOnItemClickListener(adapterViewListener);

        findViewById(R.id.avi).setVisibility(View.GONE);

    }

}
