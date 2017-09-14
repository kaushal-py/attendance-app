package com.dev.kbstudios.kbstudiosattendance;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by kbstudios on 14/9/17.
 */

public class AddClassFragment extends DialogFragment {

    private DatabaseReference mDatabase;
    private TextView classname;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("class");

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogClass = inflater.inflate(R.layout.dialog_add_class, null);
        builder.setView(dialogClass)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        classname = (TextView) dialogClass.findViewById(R.id.classname);
                        Classroom classroom = new Classroom(classname.getText().toString());
                        mDatabase.push().setValue(classroom);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
