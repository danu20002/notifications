package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
private static final String CHANNEL_Id="NEW  CHANNEL";
    private static final int  NOTIFICAtION_Id=100;
    private static final int REQ_code=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.ironman,null);
        BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
        Bitmap largeicon=bitmapDrawable.getBitmap();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,REQ_code,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        Notification.BigPictureStyle bigPictureStyle=new Notification.BigPictureStyle();
        bigPictureStyle.bigPicture(largeicon).bigLargeIcon(largeicon).setBigContentTitle("I AM REALLY IRONMAN");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification=new Notification.Builder(this).setLargeIcon(largeicon)
                    .setSmallIcon(R.drawable.ironman).setContentTitle(" HEY this is IRONMAN").setChannelId(CHANNEL_Id)
                    .setStyle(bigPictureStyle).setContentIntent(pendingIntent)
                    .build();
           notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_Id,"my channel",NotificationManager.IMPORTANCE_HIGH));

        }else{
            notification=new Notification.Builder(this).setLargeIcon(largeicon)
                    .setSmallIcon(R.drawable.ironman).setContentTitle(" HEY this is IRONMAN").setStyle(bigPictureStyle)
                    .build();
        }
        notificationManager.notify(NOTIFICAtION_Id,notification);
    }
}