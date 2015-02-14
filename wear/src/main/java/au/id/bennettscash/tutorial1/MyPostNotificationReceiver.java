package au.id.bennettscash.tutorial1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.ContextThemeWrapper;
import android.widget.Toast;


public class MyPostNotificationReceiver extends BroadcastReceiver {
    public static final String CONTENT_KEY = "contentText";

    public MyPostNotificationReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent displayIntent = new Intent(context, MyDisplayActivity.class);
        String text = intent.getStringExtra(CONTENT_KEY);
        Intent fsIntent = new Intent(context, FullScreenActivity.class);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(text)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_bud))
                .addAction(R.drawable.bg_bud, "View", PendingIntent.getActivity(context, 0, fsIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .extend(new Notification.WearableExtender()
                        .setDisplayIntent(PendingIntent.getActivity(context, 0, displayIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT)))
                .build();
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, notification);

        Toast.makeText(context, context.getString(R.string.notification_posted), Toast.LENGTH_SHORT).show();
    }
}