package com.dev.kbstudios.kbstudiosattendance;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kbstudios on 6/9/17.
 */

public class AttendeeArrayAdapter extends ArrayAdapter<Attendee> {

    private Context context;
    private ArrayList<Attendee> studentAttendees;

    public AttendeeArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Attendee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.studentAttendees = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Attendee attendee = studentAttendees.get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.attendance_card_layout, null);

        TextView rollNo = (TextView) view.findViewById(R.id.rollNo);
        TextView name = (TextView) view.findViewById(R.id.name);

        rollNo.setText(String.valueOf(attendee.getRollNo()));
        name.setText(String.valueOf(attendee.getFullName()));

        if(attendee.getStatus() == 1){
            GradientDrawable bgShape = (GradientDrawable) view.getBackground();
            bgShape.setColor(ContextCompat.getColor(context, R.color.colorGreen));
        }
        if(attendee.getStatus() == 0){
            GradientDrawable bgShape = (GradientDrawable) view.getBackground();
            bgShape.setColor(ContextCompat.getColor(context, R.color.colorPrimary1));
        }

        return view;
    }
}
