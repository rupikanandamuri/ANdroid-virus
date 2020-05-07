package com.example.andovirus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestNotListActivity extends AppCompatActivity {

    ArrayList<Patient> patientData1 = new ArrayList<Patient>();

    List<Patient> patientValue1;

    ListView patientTestNotListView;

    PatientTestNotListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_not_list);

        //set up tool bar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbarPatientNotList);
        setSupportActionBar(t1);

        //add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        patientValue1 = HomeFragment.connection2.patientTestNotDao().getAllPatients();

        patientData1 = (ArrayList<Patient>) patientValue1;

        Collections.sort(patientData1, Patient.PatientComparator);

        //add adapter

        adapter = new PatientTestNotListViewAdapter(this,R.layout.patienttestnot_layout,patientData1);

        patientTestNotListView = (ListView) findViewById(R.id.PatientTestNotListView);

        patientTestNotListView.setAdapter((ListAdapter) adapter);
    }
}
