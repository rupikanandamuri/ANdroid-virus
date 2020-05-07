package com.example.andovirus;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Patient.class},version = 1,exportSchema = false)
public abstract class PatientsTestNotDatabase extends RoomDatabase {

    public abstract  PatientTestNotDao patientTestNotDao();
}
