package com.dev.kbstudios.kbstudiosattendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstTimeLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_login);

        SharedPreferences sessionUser = getApplicationContext()
                .getSharedPreferences("kbstudiosattendance.userdata", Context.MODE_PRIVATE);
        String firebaseEmail = sessionUser.getString("user", null);
        String username = sessionUser.getString("username", null);

        final TextView textView = findViewById(R.id.user_name);
        textView.setText(username);

        Button studentButton = findViewById(R.id.student_button);
        Button teacherButton = findViewById(R.id.teacher_button);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView.getText().length() == 0){
                    Toast.makeText(FirstTimeLogin.this, "Enter your name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(FirstTimeLogin.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
            }
        });

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView.getText().length() == 0){
                    Toast.makeText(FirstTimeLogin.this, "Enter your name!", Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                    Intent i1 = new Intent(FirstTimeLogin.this, Main2Activity.class);
                    startActivity(i1);
                }
            }
        });

    }
}
