package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters.EventsMainAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters.SetAlarmsAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOFirebaseImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivitySetAlarmsBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.NotifyReceiver;

public class SetAlarms extends AppCompatActivity implements SetAlarmsAdapter.OnItemClickListener {

    private ActivitySetAlarmsBinding binding;
    private ArrayList<EventModel> eventList = new ArrayList<>();
    private SetAlarmsAdapter setAlarmsAdapter;
    private EventDAO eventDAO;
    private String userID = "";

    private Calendar cal;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetAlarmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createNotificationChannel();

        Bundle bundle = getIntent().getExtras();
        this.userID = bundle.getString("userID");
        eventDAO = new EventDAOFirebaseImpl(getApplicationContext(), this.userID);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                eventList = eventDAO.getEvents();
            }
        };
        handler.postDelayed(runnable, 1000);

        /*
            TODO
            1. Display list of events from Firebase DB
                - use RecyclerView
            2. Set OnClickListeners in each event
                - mirror design to setAlarm() in Alarm Manager project
        */

        setAlarmsAdapter = new SetAlarmsAdapter(getApplicationContext(), eventList, this);
        binding.rvSetAlarmList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvSetAlarmList.setAdapter(setAlarmsAdapter);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "alarmChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("alarm", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        super.onResume();
        eventList = eventDAO.getEvents();
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                setAlarmsAdapter.updateList(eventList);
            }
        }.start();

    }

    @Override
    public void onItemClick(int position) {
        String time = this.eventList.get(position).getTime();
        int hour = Integer.parseInt("" + time.charAt(0) + time.charAt(1));
        int minute = Integer.parseInt("" + time.charAt(2) + time.charAt(3));
        int day = Integer.parseInt(this.eventList.get(position).getDayNumber());
        int year = Integer.parseInt(this.eventList.get(position).getYearNumber());

        cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);

        switch (this.eventList.get(position).getMonthName()) {
            case "January":     cal.set(Calendar.MONTH, 0);     break;
            case "February":    cal.set(Calendar.MONTH, 1);     break;
            case "March":       cal.set(Calendar.MONTH, 2);     break;
            case "April":       cal.set(Calendar.MONTH, 3);     break;
            case "May":         cal.set(Calendar.MONTH, 4);     break;
            case "June":        cal.set(Calendar.MONTH, 5);     break;
            case "July":        cal.set(Calendar.MONTH, 6);     break;
            case "August":      cal.set(Calendar.MONTH, 7);     break;
            case "September":   cal.set(Calendar.MONTH, 8);     break;
            case "October":     cal.set(Calendar.MONTH, 9);     break;
            case "November":    cal.set(Calendar.MONTH, 10);    break;
            case "December":    cal.set(Calendar.MONTH, 11);    break;
        }

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(SetAlarms.this, NotifyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), position, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

        Snackbar.make(binding.getRoot(), "Alarm set for " + hour + ":" + minute + " (" + this.eventList.get(position).getEventTitle() + ").", Snackbar.LENGTH_SHORT).show();
    }
}