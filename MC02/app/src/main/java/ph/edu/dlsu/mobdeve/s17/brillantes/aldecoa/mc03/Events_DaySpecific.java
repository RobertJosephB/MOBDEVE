package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.AlarmClock;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Random;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters.EventAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOFirebaseImpl;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityEventsDayspecificBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class Events_DaySpecific extends AppCompatActivity {

    private ActivityEventsDayspecificBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;

    private ArrayList<EventModel> eventList;
    private EventAdapter eventAdapter;
    private String displayedMonth;
    private String year;
    private String day;
    private String email;

    private EventDAO eventDAO;
    private String newTitle,newTime, newDetails,newAlarm, newSendTo,newType, newNotificationTime, userID;
    private String shortMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventsDayspecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle extras = getIntent().getExtras();
        displayedMonth = extras.getString("monthname");
        email = extras.getString("email");

        year = extras.getString("year");
        day = extras.getString("day");
        userID = extras.getString("userID");
        Random rand = new Random();
        eventDAO = new EventDAOFirebaseImpl(getApplicationContext(),userID);
        eventList = eventDAO.getDayEvents(displayedMonth,year,day);
        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                eventAdapter.updateList(eventList);
                eventAdapter.notifyDataSetChanged();

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
        switch (displayedMonth) {
            case "January":     shortMonth = "JAN.";    break;
            case "February":    shortMonth = "FEB.";    break;
            case "March":       shortMonth = "MAR.";    break;
            case "April":       shortMonth = "APR.";    break;
            case "May":         shortMonth = "MAY";     break;
            case "June":        shortMonth = "JUNE";    break;
            case "July":        shortMonth = "JULY";    break;
            case "August":      shortMonth = "AUG.";    break;
            case "September":   shortMonth = "SEPT.";   break;
            case "October":     shortMonth = "OCT.";    break;
            case "November":    shortMonth = "NOV.";    break;
            case "December":    shortMonth = "DEC.";    break;
        }
        binding.tvName.setText(email.substring(0,6));
        binding.logoutDay.setOnClickListener( v -> {
            Intent welcome = new Intent(Events_DaySpecific.this, Welcome.class);

            startActivity(welcome);
            finish();
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                    Bundle data = result.getData().getExtras();
                    newTitle = data.getString("title");
                    newTime = data.getString("timeHr").concat(data.getString("timeMin"));
                    newDetails = data.getString("details");
                    newAlarm = data.getString("alarm");
                    newSendTo = data.getString("sendto");
                    newType = data.getString("type");
                    newNotificationTime = data.getString("ntime");


                    EventModel temp = new EventModel(day,displayedMonth,year,newTitle,newTime,newDetails,newType,newNotificationTime);
                    if(newTime.length() == 3)
                        temp.setTime("0"+newTime);
                    else
                        temp.setTime(newTime);

                    temp.setUserId(userID);
                    temp.setNotificationType(newAlarm);
                    temp.setEventId(rand.nextInt());

                    eventDAO.addEvent(temp);
                    eventList = eventDAO.getDayEvents(displayedMonth,year,day);
                    eventAdapter.updateList(eventList);
                }
            }
        });

        binding.tvCurrentMonth.setText(shortMonth);

        eventAdapter = new EventAdapter(getApplicationContext(), eventList,userID,email);

        binding.rvEventList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventList.setAdapter(eventAdapter);

        binding.addEvent.setOnClickListener( v -> {

            Intent addEvent = new Intent(Events_DaySpecific.this, AddEvent.class);
            addEvent.putExtra("monthname",extras.getString("monthname"));
            addEvent.putExtra("day",day);
            addEvent.putExtra("email",email);

            activityResultLauncher.launch(addEvent);

        });
    }
    @Override
    public void onResume()
    {
        super.onResume();
        eventList = eventDAO.getDayEvents(displayedMonth,year,day);
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                eventAdapter.updateList(eventList);
            }
        }.start();

    }






}
