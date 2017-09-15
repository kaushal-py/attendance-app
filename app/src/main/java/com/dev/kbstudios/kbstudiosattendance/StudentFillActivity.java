package com.dev.kbstudios.kbstudiosattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentFillActivity extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;

    private DatabaseReference mDatabase;

    private ArrayList<String> studentlist = new ArrayList<>();
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fill);

        String classKey = getIntent().getStringExtra("classKey");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("class").child(classKey);

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentlist);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

        ListView listView = (ListView) findViewById(R.id.student_list);
        listView.setAdapter(itemsAdapter);

        Button addStudentButton = (Button) findViewById(R.id.add_student);
        final TextView addStudentText  = (TextView) findViewById(R.id.add_student_text);

        Button doneAddingStudents = (Button) findViewById(R.id.done_adding_students);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Classroom classroom = dataSnapshot.getValue(Classroom.class);
                classroom.setKey(dataSnapshot.getKey());
                itemsAdapter.add(classroom.getClassName());
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

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = addStudentText.getText().toString();
                if(studentName.length() == 0){
                    Toast.makeText(StudentFillActivity.this, "Empty Value", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        });
}