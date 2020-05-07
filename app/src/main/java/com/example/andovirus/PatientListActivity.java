package com.example.andovirus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    ArrayList<Patient> patientData = new ArrayList<Patient>();

    List<Patient> patientValue;

    ListView patientListView;

    PatientListViewAdapter adapter;

    String name;

    int ageValue;

    int priority;

   int positionToRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        //set up tool bar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbarPatientList);
        setSupportActionBar(t1);

        //add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        patientValue = HomeFragment.connection.patientDao().getAllPatients();

        patientData = (ArrayList<Patient>) patientValue;

        Collections.sort(patientData);

        //add adapter

        adapter = new PatientListViewAdapter(this,R.layout.patient_layout,patientData);

        patientListView = (ListView) findViewById(R.id.PatientListView);

        patientListView.setAdapter(adapter);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionToRemove = position;
                alterBoxShow(positionToRemove);

            }
        });
    }

    public void alterBoxShow(final int position){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(PatientListActivity.this);
        builder1.setTitle("Patient Tested");
        builder1.setMessage("If patient Tested then press yes to remove patient from list.Else press no");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        adapter.removeAt(position);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast t = Toast.makeText(getApplication(),"Your are not setting notification",Toast.LENGTH_LONG);
                        t.show();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
