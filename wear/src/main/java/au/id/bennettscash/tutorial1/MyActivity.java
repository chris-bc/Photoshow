package au.id.bennettscash.tutorial1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MyActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
/*        Intent notificationIntent = new Intent(this, MyActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this)
//                .setContentIntent(notificationPendingIntent)
                .setContentTitle("foo")
                .setContentText("bar")
                .build();
//                .extend(new Notification.WearableExtender()
//                        .setDisplayIntent(notificationPendingIntent)
//                        .setCustomSizePreset(Notification.WearableExtender.SIZE_MEDIUM))
//                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(1, notification);
        */

        Intent i = new Intent();
        i.setAction("au.id.bennettscash.tutorial1.SHOW_NOTIFICATION");
        i.putExtra(MyPostNotificationReceiver.CONTENT_KEY, getString(R.string.title));
        sendBroadcast(i);

    }
}
