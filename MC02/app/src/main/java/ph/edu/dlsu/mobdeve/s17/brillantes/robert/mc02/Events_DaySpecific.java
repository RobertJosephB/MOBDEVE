package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters.EventAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao.EventDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEventsDayspecificBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class Events_DaySpecific extends AppCompatActivity {

    private ActivityEventsDayspecificBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;

    private ArrayList<EventModel> eventList;
    private EventAdapter eventAdapter;
    private String displayedMonth;
    private String year;
    private String day;
    private EventDAO eventDAO;
    private String newTitle,newTime, newDetails,newAlarm, newSendTo;
    private String shortMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventsDayspecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        eventDAO = new EventDAOSQLImpl(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        displayedMonth = extras.getString("monthname");
        year = extras.getString("year");
        day = extras.getString("day");
        Random rand = new Random();
        eventList = eventDAO.getDayEvents(displayedMonth,year,day);
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
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                    Bundle data = result.getData().getExtras();
                    newTitle = data.getString("title");
                    newTime = data.getString("time");
                    newDetails = data.getString("details");
                    newAlarm = data.getString("alarm");
                    newSendTo = data.getString("sendto");

                    EventModel temp = new EventModel(day,displayedMonth,year,newTitle,newTime,newDetails);
                    temp.setNotificationType(newAlarm);
                    temp.setEventId(rand.nextInt());

                    eventDAO.addEvent(temp);
                    eventList = eventDAO.getDayEvents(displayedMonth,year,day);
                    eventAdapter.updateList(eventList);

                }
            }
        });

        binding.tvCurrentMonth.setText(shortMonth);

        // dummy data
         //init();

        eventAdapter = new EventAdapter(getApplicationContext(), eventList);

        binding.rvEventList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventList.setAdapter(eventAdapter);

        binding.addEvent.setOnClickListener( v -> {

            Intent addEvent = new Intent(Events_DaySpecific.this, AddEvent.class);
            addEvent.putExtra("monthname",extras.getString("monthname"));
            activityResultLauncher.launch(addEvent);



        });


    }
    @Override
    public void onResume()
    {
        super.onResume();
        eventList = eventDAO.getDayEvents(displayedMonth,year,day);
        eventAdapter.updateList(eventList);
    }






}
