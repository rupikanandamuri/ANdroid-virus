package com.example.andovirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class PatientTestNotListViewAdapter extends ArrayAdapter<Patient> {

    private  ArrayList<Patient> patients;
    private Context context;
    private int resource;

    public PatientTestNotListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Patient> objects) {
        super(context, resource, objects);
        this.patients = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Patient patientValuesTestNot = patients.get(position);

        String nameValue = patientValuesTestNot.getPatientName();
        int ageValue = patientValuesTestNot.getPatientAge();

        //create a layout infalter
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);

        //fetch textView

        TextView name = (TextView) convertView.findViewById(R.id.nametextView2);
        TextView age = (TextView) convertView.findViewById(R.id.agetextView2);

        name.setText(nameValue);
        age.setText(Integer.toString(ageValue));


        return convertView;

    }
}
