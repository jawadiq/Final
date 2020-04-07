package com.dreamlogix.afinal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


import java.util.Random;

public class MainAlarm extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private TimePicker mtimer;
    private TextView alarmtxt_update;
    private Alarm_Receiver alarm;


    MainAlarm obj1;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alarm);
        this.context = this;
        alarmtxt_update = (TextView) findViewById(R.id.update_text);
        final Intent myIntent = new Intent(this.context, Alarm_Receiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();

        mtimer = (TimePicker) findViewById(R.id.timekeeper);


        Button start_alarm = (Button) findViewById(R.id.alarm_on);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND, 3);


                final int hour = mtimer.getCurrentHour();
                final int minute = mtimer.getCurrentMinute();
                setAlarmText("Alarm is set for " + hour + " and " + minute);

                calendar.set(Calendar.HOUR_OF_DAY, mtimer.getCurrentHour());
                calendar.set(Calendar.MINUTE, mtimer.getCurrentMinute());


                myIntent.putExtra("extra", "yes");
                pending_intent = PendingIntent.getBroadcast(MainAlarm.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                setAlarmText("Alarm set to " + hour + ":" + minute);
                Toast.makeText(getApplicationContext(), "Alarm has been set", Toast.LENGTH_SHORT).show();
            }

        });

        Button stop_alarm = (Button) findViewById(R.id.alarm_off);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
                setAlarmText("Alarm Turned off");
                Toast.makeText(getApplicationContext(), "Alarm stopped", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setAlarmText(String output) {
        alarmtxt_update.setText(output);
    }


    @Override
    public void onStart() {
        super.onStart();
        obj1 = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }

    public void toMaps(View view) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);


    }
}
