package au.id.bennettscash.tutorial1;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Random;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TimerService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SHOW_ALARM = "action_show_alarm";
    private static final String ACTION_REMOVE_ALARM = "action_remove_timer";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "au.id.bennettscash.tutorial1.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "au.id.bennettscash.tutorial1.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TimerService.class);
        intent.setAction(ACTION_SHOW_ALARM);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, TimerService.class);
        intent.setAction(ACTION_REMOVE_ALARM);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public TimerService() {
        super("TimerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SHOW_ALARM.equals(action)) {
                handleActionFoo();
            } else if (ACTION_REMOVE_ALARM.equals(action)) {
                handleActionBaz();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo() {
        NewMessageNotification.notify(this, getImage());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        long duration = pref.getLong( "bennettscash.pics.duration", 0 );

        Notifier.setupTimer(this, duration);
//        Intent intent = new Intent(this, TimerService.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz() {
        Notifier.cancelTimer(this);
    }

    public Bitmap getImage() {
        String[] projection = new String[] {MediaStore.Images.Media.DATA };

        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cur = getContentResolver().query(images, projection, null, null, null);

        final ArrayList<String> imagesPath = new ArrayList<String>();
        if (cur.moveToFirst()) {
            int dataColumn = cur.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                imagesPath.add(cur.getString(dataColumn));
            } while (cur.moveToNext());
        }
        cur.close();
        final Random random = new Random();
        final int count = imagesPath.size();
        int number = random.nextInt(count);
        String path = imagesPath.get(number);
        return BitmapFactory.decodeFile(path);
    }
}
