package com.example.profilemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class SelectTime extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button save;
    private TextView textView1;
    private TextView textView2;
    private RadioGroup radioGroup;
    private Switch saturday;
    private Switch sunday;
    private Switch monday;
    private Switch tuesday;
    private Switch wednesday;
    private Switch thursday;
    private Switch friday;
    private String startTime;
    private String endTime;
    private String days ="";
    private int mode ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        button1 = (Button) findViewById(R.id.startTimeButton);
        button2 = (Button) findViewById(R.id.endtTimeButton);
        // et_name = (EditText)findViewById(R.id.etname);
        save = (Button) findViewById(R.id.saveButton);

        textView1 = (TextView) findViewById(R.id.startTimeView);
        textView2 = (TextView) findViewById(R.id.endTimeView);

        saturday = (Switch) findViewById(R.id.saturday);
        sunday = (Switch) findViewById(R.id.sunday);
        monday = (Switch) findViewById(R.id.monday);
        tuesday = (Switch) findViewById(R.id.tuesday);
        wednesday = (Switch) findViewById(R.id.wednesday);
        thursday = (Switch) findViewById(R.id.thursday);
        friday = (Switch) findViewById(R.id.friday);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupp);
    }
}
