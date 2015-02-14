package au.id.bennettscash.tutorial1;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by chris on 9/10/2014.
 */
public class Notifier {
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setupTimer(Context t, long duration) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(t);
        nm.cancel(1);
        AlarmManager alarmManager = (AlarmManager) t.getSystemService(Context.ALARM_SERVICE);
        long time = System.currentTimeMillis() + (duration * 60 * 1000);
        Intent intent = new Intent("action_show_alarm", null, t, TimerService.class);
        PendingIntent pendingIntent = PendingIntent.getService(t, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public static void cancelTimer(Context t) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from( t );
        notificationManager.cancel( 1 );

        AlarmManager alarmManager = (AlarmManager) t.getSystemService( Context.ALARM_SERVICE );

        Intent intent = new Intent( "action_show_alarm", null, t, TimerService.class );
        PendingIntent pendingIntent = PendingIntent.getService( t, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT );

        alarmManager.cancel( pendingIntent );
    }
}
