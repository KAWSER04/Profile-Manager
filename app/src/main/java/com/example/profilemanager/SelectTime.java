package com.example.profilemanager;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(2);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton(v);

            }
        });

    }

    private void swithButton() {
        if(saturday.isChecked()){
            days+="7,";
        }
        if(sunday.isChecked()){
            days+="1,";
        }
        if(monday.isChecked()){
            days+="2,";
        }
        if(tuesday.isChecked()){
            days+="3,";
        }
        if(wednesday.isChecked()){
            days+="4,";
        }
        if(thursday.isChecked()){
            days+="5,";
        }
        if(friday.isChecked()){
            days+="6,";
        }
    }

    public void checkButton (View view){

        int radioid = radioGroup.getCheckedRadioButtonId();
        switch (radioid){
            case R.id.silent:
                break;
            case R.id.vibrate:
                break;
        }
    }

    public void timePicker(final int n){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                
                if(n==1){
                    textView1.setText(hourOfDay + " : " +minute);
                    startTime=hourOfDay + ":" +minute;
                }
                else{
                    textView2.setText(hourOfDay + " : " +minute);
                    endTime=hourOfDay + ":" +minute;
                }
            }
        },12,00,android.text.format.DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }
}
