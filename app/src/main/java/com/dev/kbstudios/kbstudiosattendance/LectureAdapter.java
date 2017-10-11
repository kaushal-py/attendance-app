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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kbstudios on 6/9/17.
 */

public class LectureAdapter extends ArrayAdapter<Lecture> {

    private Context context;
    private ArrayList<Lecture> lectures;

    public LectureAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Lecture> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lectures = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Lecture lecture = lectures.get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.attendance_menu_layout, null);

        TextView lectureNo = (TextView) view.findViewById(R.id.lecture_number);
        TextView lectureDate = (TextView) view.findViewById(R.id.lecture_date);
        TextView lectureTime = (TextView) view.findViewById(R.id.lecture_time);

        lectureNo.setText("Lecture " + String.valueOf(position + 1));

        Date date = new Date(lecture.getYear(), lecture.getMonth(), lecture.getDay(),
                lecture.getHourOfDay(), lecture.getMinute());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

        lectureDate.setText(dateFormat.format(date));
        lectureTime.setText(timeFormat.format(date));

        return view;
    }
}
