package app.iecs.fcu.android_hw4_2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class fcuReceiver extends BroadcastReceiver {

    //@Override
    @SuppressLint("NewApi")
    static int theId = 5500;

    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("KEY_message");

        Intent myIntent = new Intent();
        myIntent.setClass(context,MainActivity.class);
        myIntent.putExtra("Name",message);

        PendingIntent PI = PendingIntent.getActivity(context,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //測試用Toast
        Toast.makeText(context, "HW4.2\n廣播已接收 : "+message, Toast.LENGTH_SHORT).show();

        //建立提醒內容
        Notification.Builder myBuilder = new Notification.Builder(context);
        myBuilder.setContentText("Hello");
        myBuilder.setContentText(message);
        myBuilder.setSmallIcon(R.mipmap.ic_launcher);
        myBuilder.setContentIntent(PI); //設定點擊內容

        //建立提醒物件
        Notification myNotify = myBuilder.build();

        //宣告提醒管理員
        NotificationManager myManager = (NotificationManager)context.
                        getSystemService(Context.NOTIFICATION_SERVICE);

        //發送提醒
        myManager.notify(theId,myNotify);



        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
