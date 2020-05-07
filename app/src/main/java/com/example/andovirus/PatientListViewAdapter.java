package com.example.andovirus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class PatientListViewAdapter extends ArrayAdapter<Patient> {

    private    ArrayList<Patient> patients;
    private Context context;
    private int resource;

    public PatientListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Patient> objects) {
        super(context, resource, objects);
        patients = objects;
        this.context = context;
        this.resource = resource;
    }

    public void removeAt(int index){
        patients.remove(index);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        List<Patient> getPatientValues;
//
//        getPatientValues = HomeFragment.connection.patientDao().getAllPatients();

        Patient patientValues = patients.get(position);

        String nameValue = patientValues.getPatientName();
        int ageValue = patientValues.getPatientAge();
        int priorityValue = patientValues.getPriority();

        //create a layout infalter
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource,parent,false);

        //fetch textView

        TextView name = (TextView) convertView.findViewById(R.id.nametextView);
        TextView age = (TextView) convertView.findViewById(R.id.agetextView);
        TextView priorty = (TextView) convertView.findViewById(R.id.prioritytextView);

        if(priorityValue == 3){
            name.setTextColor(Color.parseColor("#ff0000"));
            age.setTextColor(Color.parseColor("#ff0000"));
            priorty.setTextColor(Color.parseColor("#ff0000"));
        }
        else  if(priorityValue == 2){
            name.setTextColor(Color.parseColor("#ffa500"));
            age.setTextColor(Color.parseColor("#ffa500"));
            priorty.setTextColor(Color.parseColor("#ffa500"));
        }
        else if(priorityValue == 1){
            name.setTextColor(Color.parseColor("#008000"));
            age.setTextColor(Color.parseColor("#008000"));
            priorty.setTextColor(Color.parseColor("#008000"));
        }

        name.setText(nameValue);
        age.setText(Integer.toString(ageValue));
        priorty.setText(Integer.toString(priorityValue));

        return convertView;
    }
}
