package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.adapters.EventsMainAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOFirebaseImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityEventsMainBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.CalendarModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.DayModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.MonthModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.StoragePreferences;

public class Events_Main extends AppCompatActivity {

    private ActivityEventsMainBinding binding;
    private CalendarModel calendar;
    private EventsMainAdapter eventsMainAdapter;
    private ArrayList<DayModel> currentMonthDays;
    private String currentMonth;
    private String currentYear;
    private StoragePreferences storagePreferences;
    private String LOG_TAG = "Events_Main";
    private String userID = "";
    private String email = "";
    private EventsMainAdapter.ViewClickListener listener;
    private EventDAO eventDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(LOG_TAG, "onCreate()");


        super.onCreate(savedInstanceState);
        binding = ActivityEventsMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        userID = extras.getString("userID");
        email = extras.getString("email");
        eventDAO  = new EventDAOFirebaseImpl(getApplicationContext(),userID);



        this.calendar = new CalendarModel();
        this.storagePreferences = new StoragePreferences(this);

        Date dNow = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        String dNowMonth = "" + month.format(dNow);
        String dNowYear = "" + year.format(dNow);
        int currentMonthIndex = -1;
        int currentYearIndex = -1;
        String displayedMonth = "";
        String displayedYear = "";


        int i;

        for (i = 0; i < calendar.getYears().size() && currentYearIndex == -1; i++) {
            if (calendar.getYears().get(i).getYearNum().equals(dNowYear))
                currentYearIndex = i;
        }

        this.currentYear = calendar.getYears().get(currentYearIndex).getYearNum();
        displayedYear = this.currentYear;

        ArrayList<MonthModel> tempCurrentYear = calendar.getYears().get(currentYearIndex).getMonths();

        for (i = 0; i < tempCurrentYear.size() && currentMonthIndex == -1; i++) {
            if (tempCurrentYear.get(i).getMonthNum().equals(dNowMonth))
                currentMonthIndex = i;
        }

        this.currentMonth = tempCurrentYear.get(currentMonthIndex).getMonthName();
        this.currentMonthDays = tempCurrentYear.get(currentMonthIndex).getDays();



        switch (this.currentMonth) {
            case "January":     displayedMonth = "JAN.";    break;
            case "February":    displayedMonth = "FEB.";    break;
            case "March":       displayedMonth = "MAR.";    break;
            case "April":       displayedMonth = "APR.";    break;
            case "August":      displayedMonth = "AUG.";    break;
            case "September":   displayedMonth = "SEPT.";   break;
            case "October":     displayedMonth = "OCT.";    break;
            case "November":    displayedMonth = "NOV.";    break;
            case "December":    displayedMonth = "DEC.";    break;
        }

        binding.tvCurrentMonth.setText(displayedMonth);
        binding.tvCurrentYear.setText(displayedYear);
        if(email.length()>=6) {
            binding.tvName.setText(email.substring(0, 6));
        }
        else
            binding.tvName.setText(email);

        this.storagePreferences.saveStringPreferences("oldCurrentMonth", this.currentMonth);
        this.storagePreferences.saveStringPreferences("currentMonth", this.currentMonth);
        this.storagePreferences.saveStringPreferences("oldCurrentYear", this.currentYear);
        this.storagePreferences.saveStringPreferences("currentYear", this.currentYear);



        setOnClickListener();

        eventsMainAdapter = new EventsMainAdapter(getApplicationContext(), this.currentMonthDays,listener);
        binding.rvEventMainList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventMainList.setAdapter(eventsMainAdapter);


        binding.tvCurrentMonth.setOnClickListener( v -> {
            Intent changeMonth = new Intent(Events_Main.this, ChangeMonth.class);

            changeMonth.putExtra("calendar", this.calendar);

            startActivity(changeMonth);
        });
        binding.logoutMain.setOnClickListener( v -> {
            Intent welcome = new Intent(Events_Main.this, Welcome.class);

            startActivity(welcome);
            finish();
        });
        Handler handler_data = new Handler();

        Runnable runnable_data = new Runnable() {
            @Override
            public void run() {

                for(int j = 0; j < currentMonthDays.size(); j++){
                    ArrayList<EventModel> temp = eventDAO.getDayEvents(currentMonth,currentYear,currentMonthDays.get(j).getDayNumber());

                    int finalJ = j;
                    new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            try {
                                temp.get(0).getMonthName();
                                currentMonthDays.get(finalJ).setEvents(temp);
                                eventsMainAdapter.updateList(currentMonthDays);

                                eventsMainAdapter.notifyDataSetChanged();

                            } catch (java.lang.IndexOutOfBoundsException e) {

                            }
                        }
                    }.start();


                }
                handler_data.postDelayed(this, 1000);
            }
        };
        handler_data.postDelayed(runnable_data, 4000);
        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                eventsMainAdapter.updateList(currentMonthDays);


                eventsMainAdapter.notifyDataSetChanged();

                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);

    }

    private void setOnClickListener() {
        listener = new EventsMainAdapter.ViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(Events_Main.this,Events_DaySpecific.class);
                intent.putExtra("monthname",currentMonth);
                intent.putExtra("day",""+(position+1));
                intent.putExtra("year",currentYear);
                intent.putExtra("userID", userID);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onResume() {

        Log.i(LOG_TAG, "onResume()");
        super.onResume();
        ArrayList<EventModel> temp = new ArrayList<>();
        EventModel noEvents = new EventModel();
        noEvents.setEventTitle("No events");
        temp.add(noEvents);
        for(int j = 0; j < currentMonthDays.size(); j++)
            currentMonthDays.get(j).setEvents(temp);



        if (!this.storagePreferences.getStringPreferences("oldCurrentMonth").equals(this.storagePreferences.getStringPreferences("currentMonth"))
                || !this.storagePreferences.getStringPreferences("oldCurrentYear").equals(this.storagePreferences.getStringPreferences("currentYear"))) {

            String displayedMonth = this.storagePreferences.getStringPreferences("currentMonth");
            currentMonth = displayedMonth;
            switch (displayedMonth) {
                case "January":
                    displayedMonth = "JAN.";
                    break;
                case "February":
                    displayedMonth = "FEB.";
                    break;
                case "March":
                    displayedMonth = "MAR.";
                    break;
                case "April":
                    displayedMonth = "APR.";
                    break;
                case "May":
                    displayedMonth = "MAY";
                    break;
                case "June":
                    displayedMonth = "JUNE";
                    break;
                case "July":
                    displayedMonth = "JULY";
                    break;
                case "August":
                    displayedMonth = "AUG.";
                    break;
                case "September":
                    displayedMonth = "SEPT.";
                    break;
                case "October":
                    displayedMonth = "OCT.";
                    break;
                case "November":
                    displayedMonth = "NOV.";
                    break;
                case "December":
                    displayedMonth = "DEC.";
                    break;
            }

            binding.tvCurrentMonth.setText(displayedMonth);
            binding.tvCurrentYear.setText(this.storagePreferences.getStringPreferences("currentYear"));


            int i;
            int currentYearIndex = -1;
            int currentMonthIndex = -1;

            for (i = 0; i < calendar.getYears().size() && currentYearIndex == -1; i++) {
                if (calendar.getYears().get(i).getYearNum().equals(this.storagePreferences.getStringPreferences("currentYear")))
                    currentYearIndex = i;
            }

            this.currentYear = calendar.getYears().get(currentYearIndex).getYearNum();

            ArrayList<MonthModel> tempCurrentYear = calendar.getYears().get(currentYearIndex).getMonths();

            for (i = 0; i < tempCurrentYear.size() && currentMonthIndex == -1; i++) {
                if (tempCurrentYear.get(i).getMonthName().equals(this.storagePreferences.getStringPreferences("currentMonth")))
                    currentMonthIndex = i;
            }

            this.currentMonthDays = tempCurrentYear.get(currentMonthIndex).getDays();
            this.storagePreferences.saveStringPreferences("oldCurrentMonth", this.storagePreferences.getStringPreferences("currentMonth"));
            this.storagePreferences.saveStringPreferences("oldCurrentYear", this.storagePreferences.getStringPreferences("currentYear"));


        }
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "onPause()");
        super.onPause();
    }

}