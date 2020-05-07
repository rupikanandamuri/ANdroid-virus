package com.example.andovirus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        //set up tool bar
        Toolbar t1 = (Toolbar) findViewById(R.id.toolbarList);
        setSupportActionBar(t1);

        //add an up bar
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();

        Bundle bundle = i.getExtras();

        if(bundle!= null){
            int waitingValue = bundle.getInt("waitingKey");
            int priorityValue = bundle.getInt("priorityKey");
            System.out.println(waitingValue);
            System.out.println(priorityValue);
            TextView wait = (TextView) findViewById(R.id.textView8);
            wait.setText(String.valueOf(waitingValue));

            TextView priority = (TextView) findViewById(R.id.textView4);
            priority.setText(String.valueOf(priorityValue));

            TextView output = (TextView) findViewById(R.id.textView6);

            if(priorityValue > 0){
                String value1 = "You Required Test.";
                output.setText(value1);
            }
            else if(priorityValue == 0){
                String value2 = "You Not Required Test.";
                output.setText(value2);
                wait.setVisibility(View.INVISIBLE);
                priority.setVisibility(View.INVISIBLE);
            }

        }

    }
}
