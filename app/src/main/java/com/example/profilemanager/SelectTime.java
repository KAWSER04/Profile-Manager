package com.example.profilemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

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
    SqlHelper sqlHelper;
    public EditText et_name;
    Random random;
    private int session_key_start;
    private int session_key_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
        sqlHelper = new SqlHelper(getApplicationContext());
        random = new Random();

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
                swithButton();

                setAlerm(startTime,0);
                setAlerm(endTime,1);
                StoreInDatabase();
                Intent intent = new Intent(SelectTime.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
    private void setAlerm(String st,int event) {
        Toast.makeText(getApplicationContext(),"str" + st ,Toast.LENGTH_LONG).show();
        Log.i("mytag",st);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] split = st.split(":");
            int hour = Integer.parseInt(split[0]);
            int minit = Integer.parseInt(split[1]);


            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minit);
            calendar.set(Calendar.SECOND,0);
            String[] days_of_week = days.split(",");
            for(int i=0;i<days_of_week.length;i++){
                int pos = Integer.parseInt(days_of_week[i]);
                switch (pos){
                    case 7:
                        calendar.set(Calendar.DAY_OF_WEEK,7);
                        break;
                    case 1:
                        calendar.set(Calendar.DAY_OF_WEEK,1);
                        break;
                    case 2:
                        calendar.set(Calendar.DAY_OF_WEEK,2);
                        break;
                    case 3:
                        calendar.set(Calendar.DAY_OF_WEEK,3);
                        break;
                    case 4:
                        calendar.set(Calendar.DAY_OF_WEEK,4);
                        break;
                    case 5:
                        calendar.set(Calendar.DAY_OF_WEEK,5);
                        break;
                    case 6:
                        calendar.set(Calendar.DAY_OF_WEEK,6);
                        break;

                }
            }


            Toast.makeText(getApplicationContext(),"Hour : " + hour + "\n" + "Min : "+ minit,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),AlermReceiver.class);


            int requestcode = random.nextInt(1000);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),requestcode,intent,0);

            if(event==0){
                session_key_start = requestcode;
                intent.putExtra("mode",0);
            }else {
                session_key_end = requestcode;
                intent.putExtra("mode",1);
            }

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
    }

    private void StoreInDatabase() {
        String name = et_name.getText().toString().trim();
        ItemsList itemsList = new ItemsList(1,name,startTime,endTime,days,mode,session_key_start,session_key_end);
        if(sqlHelper.insert(itemsList)){
            Toast.makeText(getApplicationContext(),"Insert successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Failed to Insert",Toast.LENGTH_LONG).show();
        }
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
