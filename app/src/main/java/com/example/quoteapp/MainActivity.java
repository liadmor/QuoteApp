package com.example.quoteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startQuote = (Button)findViewById(R.id.button);
        startQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarm();
            }
        });

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        /*startQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quotes allQuotes = new Quotes();
                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "My Notification");
                builder.setContentTitle("My Notification");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setContentText(allQuotes.getRandomeQuote());
                builder.setAutoCancel(true);
                manager.notify(1,builder.build());
            }
        });*/

    }

    private void startAlarm() {
        Intent myIntent;
        PendingIntent pendingIntent;

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        myIntent = new Intent(MainActivity.this, AlarmReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);
    }
}