package com.example.profilemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<ItemsList> arrayList;

    public CustomAdapter(Context context, ArrayList<ItemsList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.customadapter,null);
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView tv_startTime = (TextView)view.findViewById(R.id.tv_start);
        TextView tv_endTime = (TextView)view.findViewById(R.id.tv_end);
        ImageButton ibnt_delete = (ImageButton)view.findViewById(R.id.ibtn_delete);

        String title  = arrayList.get(position).getName();
        String startTime = "Start Time : " + arrayList.get(position).getStart();
        String endTime = "End Time : "+ arrayList.get(position).getEnd();

        tv_title.setText(title);
        tv_startTime.setText(startTime);
        tv_endTime.setText(endTime);

        ibnt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = arrayList.get(position).getId_();
                SqlHelper sqlHelper = new SqlHelper(context);
                sqlHelper.delete(id);

                Intent intent = new Intent(context,AlermReceiver.class);
                intent.putExtra("mode",-1);

                int requestcode_start = arrayList.get(position).getSession_key_start();
                int requestcode_end = arrayList.get(position).getSession_key_end();

                PendingIntent pendingIntent_start = PendingIntent.getBroadcast(context,requestcode_start,intent,0);
                AlarmManager alarmManager_start = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                alarmManager_start.cancel(pendingIntent_start);

                PendingIntent pendingIntent_end = PendingIntent.getBroadcast(context,requestcode_end,intent,0);
                AlarmManager alarmManager_end = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                alarmManager_end.cancel(pendingIntent_end);

                Intent intent_1 = new Intent(context,MainActivity.class);
                context.startActivity(intent_1);
            }
        });

        return view;
    }

}
