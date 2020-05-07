package com.example.andovirus;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    public  void addPatient(Patient patient);

    @Delete
    public  void deletePatient(Patient patient);

    @Query("Select * from patients")
    List<Patient> getAllPatients();

    @Update
    public void updateUser(Patient patient);
}
