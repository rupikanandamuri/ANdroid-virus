package com.example.andovirus;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;
import  java.util.Date;

@Entity(tableName = "patients")

public class Patient implements  Comparable<Patient> {

    @PrimaryKey @NonNull
    @ColumnInfo
    private String patientName;

    private int patientAge;

    private boolean travelled;

    private int priority;

    private int waitingNumber;

//constructor
    public Patient(@NonNull String patientName,int patientAge,boolean travelled,int priority){
        this.patientName = patientName;
        this.travelled = travelled;
        this.patientAge = patientAge;
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient pa){
       //int getPriority1 = Integer.compare(this.priority,pa.priority);
            int comparePirority = ((Patient)pa).priority;
            return comparePirority - this.getPriority();
    }

    //to compare
    public static Comparator<Patient> PatientComparator = new Comparator<Patient>() {

        public int compare(Patient p1,Patient p2) {

            String patientName1 = p1.getPatientName();
            String patientName2 = p2.getPatientName();

            //ascending order
            return patientName1.compareTo(patientName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};



    //getter and setter methods.

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public Boolean getTravelled() {
        return travelled;
    }

    public void setTravelled(Boolean travelled) {
        this.travelled = travelled;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingNumber() {
        return waitingNumber;
    }

    public void setWaitingNumber(int waitingNumber) {
        this.waitingNumber = waitingNumber;
    }
}
