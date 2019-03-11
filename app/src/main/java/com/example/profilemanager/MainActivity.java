package com.example.profilemanager;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    public static int ON_DO_NOT_DISTURB_CALLBACK_CODE = 0;
    private ListView listView;
    private ArrayList<ItemsList> items;
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Profile Scheduler");

        init_functions();
        sqlHelper = new SqlHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.listV);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingButton);

        items = new ArrayList<>();

        Cursor cursor = sqlHelper.show();
        cursor.moveToFirst();

        for( int i = 0;i<cursor.getCount();i++){

            int id = cursor.getInt(0);
            Log.i("mytag", "onCreate: "+id);

            String name = cursor.getString(1);
            Log.i("mytag", "onCreate: "+name);
            String start_time = cursor.getString(2);
            String end_time = cursor.getString(3);
            String days_ = cursor.getString(4);

            int mode = cursor.getInt(5);
            int session_key_start = cursor.getInt(6);
            int session_key_end = cursor.getInt(7);

            ItemsList itemsList = new ItemsList(id,name,start_time,end_time,days_,mode,session_key_start,session_key_end);
            items.add(itemsList);
            cursor.moveToNext();

        }

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),items);
        customAdapter.notifyDataSetChanged();
        listView.setAdapter(customAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            //Floating button click listener
            public void onClick(View view){
                openSelectTime();
            }
        });
    }

    public void openSelectTime(){
        Intent intent = new Intent(MainActivity.this,SelectTime.class); ;
        //intent.putExtra(Intent.EXTRA_TEXT,string);
        startActivity(intent);
    }

    private void init_functions() {
        try {
            if (Build.VERSION.SDK_INT < 23) {
//              Auto Permission
            } else if( Build.VERSION.SDK_INT >= 23 ) {
                this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp(getApplicationContext());
            }
        } catch ( SecurityException e ) {

        }
    }

    private void requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp(Context context) {
        //TO SUPPRESS API ERROR MESSAGES IN THIS FUNCTION, since Ive no time to figrure our Android SDK suppress stuff
        if( Build.VERSION.SDK_INT < 23 ) {
            return;
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;

        if ( notificationManager.isNotificationPolicyAccessGranted()) {
        } else{
            // Ask the user to grant access
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult( intent, MainActivity.ON_DO_NOT_DISTURB_CALLBACK_CODE );
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
