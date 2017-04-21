package app.iecs.fcu.android_hw4_2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

public class fcuReceiver extends BroadcastReceiver {

    //@Override //不知道為何此處Override會出錯
    @SuppressLint("NewApi") //新版通知 (api>16)
    static int theId = 5500; //通知編號

    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context, "HW4.2\n廣播已接收 : "+message, Toast.LENGTH_SHORT).show();
        //測試用Toast

        String message = intent.getStringExtra("KEY_message");
        Intent myIntent = new Intent();
        myIntent.setClass(context,MainActivity.class);
        myIntent.putExtra("Name",message);

        PendingIntent PI = PendingIntent.getActivity(context,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        //將設定好的 myIntent 導入 PendingIntent

        //震動時間宣告
        long[]  vibrate_effect =
                {80, 350, 100, 120, 80, 120}; //跟 LINE 一樣的頻率

        //取得系統音效
        Uri mySoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //系統預設通知音效

        //利用 Notification Builder 建立 Notification 內容
        Notification.Builder myBuilder = //建立 Notification Builder 物件 (myBuilder)
                new Notification.Builder(context); //new Builder
        myBuilder
                .setWhen(System.currentTimeMillis()) //設定通知發送時間 (立即)
                .setContentTitle("Hello") //設定通知標題
                .setContentText(message) //設定通知內容
                .setSmallIcon(R.mipmap.ic_launcher) //設定通知小圖示
                .setContentIntent(PI) //設定通知點擊內容
                .setAutoCancel(true) //設定單次通知 (點擊後消失)
                .setVibrate(vibrate_effect) //設定通知震動
                .setSound(mySoundUri); //設定通知音效

        Notification myNotify = //建立 Notification 物件 (myNotify)
                myBuilder.build(); //將 Notification Builder 建立的 Notification 內容載入至 Notification 中

        NotificationManager myManager = //建立 Notification Manager 物件 (myManager)
                (NotificationManager)context.
                        getSystemService(Context.NOTIFICATION_SERVICE); //取得系統通知服務

        myManager.notify(theId,myNotify); //推送通知
    }
}

