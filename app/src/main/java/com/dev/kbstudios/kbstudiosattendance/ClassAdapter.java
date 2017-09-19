package com.dev.kbstudios.kbstudiosattendance;

import android.app.Activity;
import android.content.Context;
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
 * Created by kbstudios on 19/9/17.
 */

public class ClassAdapter extends ArrayAdapter<Classroom> {

    private Context context;
    private ArrayList<Classroom> classList;

    public ClassAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Classroom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.classList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Classroom classroom = classList.get(position);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.class_card, null);

        TextView className = (TextView) view.findViewById(R.id.class_name);

        className.setText(String.valueOf(classroom.getClassName()));


        return view;
    }
}

