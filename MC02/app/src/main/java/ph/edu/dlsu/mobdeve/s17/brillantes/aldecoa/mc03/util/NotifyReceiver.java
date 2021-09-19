package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.Welcome;

public class NotifyReceiver extends BroadcastReceiver {
    String alarmPath = "";
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, Welcome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, i,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("MySchedule Notification")
                .setContentText("Please check your events.")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        vib.vibrate(500);
        MediaPlayer mediaPlayer;
        if(alarmPath.equals(""))
            mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
        else
            mediaPlayer = MediaPlayer.create(context, Uri.parse(Environment.getExternalStorageDirectory().getPath()+ alarmPath));
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                mediaPlayer.stop();
            }
        }.start();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());

    }

}
