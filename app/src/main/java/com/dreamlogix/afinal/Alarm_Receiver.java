package com.dreamlogix.afinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String my_string = intent.getExtras().getString("extra");
        Log.e("Key", my_string);

        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        service_intent.putExtra("extra" , my_string);
        context.startService(service_intent);
    }
}
