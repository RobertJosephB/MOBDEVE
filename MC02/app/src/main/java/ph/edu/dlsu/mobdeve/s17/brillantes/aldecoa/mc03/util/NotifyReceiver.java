package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.Events_Main;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.R;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.Welcome;

public class NotifyReceiver extends BroadcastReceiver {
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

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());

    }
}
