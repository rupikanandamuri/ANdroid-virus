package com.example.andovirus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static androidx.room.Room.databaseBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button datePickerPressed;
    Button submitDetailsPressed;
    TextView agePersons;
    EditText etname;
    String name;
    Switch simpleSwitch;
    int valuePriority;
    Boolean areYouTravelled;
    int ageValue;
    public static PatientsDatabase connection;
    public static PatientsTestNotDatabase connection2;
    Intent i ;


    public static PatientsTestNotDatabase testNotConnection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      final View view = inflater.inflate(R.layout.fragment_home,container,false);
      //connection databse
      connection = Room.databaseBuilder(getContext(),PatientsDatabase.class,"patients").allowMainThreadQueries().build();

      connection2 = Room.databaseBuilder(getContext(),PatientsTestNotDatabase.class,"patientsTestNot").allowMainThreadQueries().build();
      datePickerPressed = view.findViewById(R.id.pickDatebirth);

        agePersons = view.findViewById(R.id.ageid);
        etname = (EditText) view.findViewById(R.id.nameedit);

        simpleSwitch = (Switch) view.findViewById(R.id.switch1);


        datePickerPressed.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final Calendar c = Calendar.getInstance();
              int year = c.get(Calendar.YEAR);
              int month = c.get(Calendar.MONTH);
              int date = c.get(Calendar.DAY_OF_MONTH);
              DatePickerDialog dateDiaglog = new DatePickerDialog(view.getContext(),datePickerListner,year,month,date);
              dateDiaglog.getDatePicker().setMaxDate(new Date().getTime());
              dateDiaglog.show();
          }
      });

     //submit details
     submitDetailsPressed = view.findViewById(R.id.submitButtonid);
     submitDetailsPressed.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v) {
             name = etname.getText().toString();

             if(checkEmpty(name) == true &&  checkAgeEmpty(agePersons) == true){
             Toast t = Toast.makeText(getContext(),"Check  your name  and age filed is empty ",Toast.LENGTH_LONG);
             t.show();
         }else if(checkEmpty(name) == true){
             Toast t = Toast.makeText(getContext(),"Check  your name filed is empty ",Toast.LENGTH_LONG);
             t.show();
         }
         else if( checkAgeEmpty(agePersons) == true){
             Toast t = Toast.makeText(getContext(),"Check  your age filed is empty ",Toast.LENGTH_LONG);
             t.show();
         }
         else{
             addDetailsToDataBase(name,ageValue,simpleSwitch);
             Toast t = Toast.makeText(getContext(),"your details submitted sucessfully",Toast.LENGTH_LONG);
             t.show();
         }

         }
     });
     etname.setText("");
     agePersons.setText("");
     simpleSwitch.setChecked(false);




      return view;
    }

    private  DatePickerDialog.OnDateSetListener datePickerListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c= Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH,day);
            String format = new SimpleDateFormat("YYY MM dd").format(c.getTime());
            if(calculateAge(c.getTimeInMillis()) <= 0) {
                Toast t = Toast.makeText(getContext(),"Date of birth you entered is invalid",Toast.LENGTH_LONG);
                t.show();
            }
            else {
                agePersons.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
                ageValue = calculateAge(c.getTimeInMillis());
            }
        }
    };
    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){

            age--;
        }
        return  age;
    }

    //If string is empty/null return true, else return false
    public boolean checkEmpty(String name){

            if (!name.equals("")){
                return false;
            }

        return true;
    }
    public  boolean checkAgeEmpty(TextView agePersons){
        if(agePersons.length() == 0){

        return true;
    }
         return false;
    }

    public void addDetailsToDataBase(String name,int ageValue,Switch simpleSwitch){
        name = etname.getText().toString();
        if (ageValue > 65 && simpleSwitch.isChecked() == true) {
            valuePriority = 3;
            areYouTravelled = simpleSwitch.isChecked();

        }
       else if(ageValue > 65) {
            valuePriority = 2;
            areYouTravelled = simpleSwitch.isChecked();
        }
       else if (simpleSwitch.isChecked() == true){
            valuePriority = 1;
            areYouTravelled = simpleSwitch.isChecked();
        }
       else if(ageValue <= 65 && simpleSwitch.isChecked() == false) {
            valuePriority = 0;
            areYouTravelled = false;
        }
       Patient p = new Patient(name,ageValue,areYouTravelled,valuePriority);
        System.out.println(p);

       if(p.getPriority() > 0){
           HomeFragment.connection.patientDao().addPatient(p);
           List<Patient> patientTotal = HomeFragment.connection.patientDao().getAllPatients();
           p.setWaitingNumber(patientTotal.size());
           int waitingNo = p.getWaitingNumber();
           int priority = p.getPriority();
           i  = new Intent(getActivity(),SecondScreenActivity.class);
           Bundle bundle = new Bundle();
           bundle.putInt("waitingKey",waitingNo);
           bundle.putInt("priorityKey",priority);
           i.putExtras(bundle);
           startActivity(i);
       }else {
          HomeFragment.connection2.patientTestNotDao().addPatient(p);
           int priority = p.getPriority();
           int waitingNo = 0;
           i  = new Intent(getActivity(),SecondScreenActivity.class);
           Bundle bundle = new Bundle();
           bundle.putInt("waitingKey",waitingNo);
           bundle.putInt("priorityKey",priority);
           i.putExtras(bundle);
           startActivity(i);
       }

    }
}
