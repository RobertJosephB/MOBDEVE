package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters.EventsMainAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEventsMainBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.CalendarModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.DayModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.MonthModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.util.StoragePreferences;

public class Events_Main extends AppCompatActivity {

    private ActivityEventsMainBinding binding;
    private CalendarModel calendar;
    private EventsMainAdapter eventsMainAdapter;
    private ArrayList<DayModel> currentMonthDays;
    private String currentMonth;
    private String currentYear;
    private StoragePreferences storagePreferences;
    private String LOG_TAG = "Events_Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        binding = ActivityEventsMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        this.storagePreferences.saveStringPreferences("oldCurrentMonth", this.currentMonth);
        this.storagePreferences.saveStringPreferences("currentMonth", this.currentMonth);
        this.storagePreferences.saveStringPreferences("oldCurrentYear", this.currentYear);
        this.storagePreferences.saveStringPreferences("currentYear", this.currentYear);

        eventsMainAdapter = new EventsMainAdapter(getApplicationContext(), this.currentMonthDays);
        binding.rvEventMainList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventMainList.setAdapter(eventsMainAdapter);

        binding.tvCurrentMonth.setOnClickListener( v -> {
            Intent changeMonth = new Intent(Events_Main.this, ChangeMonth.class);

            changeMonth.putExtra("calendar", this.calendar);

            startActivity(changeMonth);
        });
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "onResume()");
        super.onResume();

        if (!this.storagePreferences.getStringPreferences("oldCurrentMonth").equals(this.storagePreferences.getStringPreferences("currentMonth"))
            || !this.storagePreferences.getStringPreferences("oldCurrentYear").equals(this.storagePreferences.getStringPreferences("currentYear"))) {

            String displayedMonth = this.storagePreferences.getStringPreferences("currentMonth");

            switch (displayedMonth) {
                case "January":     displayedMonth = "JAN.";    break;
                case "February":    displayedMonth = "FEB.";    break;
                case "March":       displayedMonth = "MAR.";    break;
                case "April":       displayedMonth = "APR.";    break;
                case "May":         displayedMonth = "MAY";     break;
                case "June":        displayedMonth = "JUNE";    break;
                case "July":        displayedMonth = "JULY";    break;
                case "August":      displayedMonth = "AUG.";    break;
                case "September":   displayedMonth = "SEPT.";   break;
                case "October":     displayedMonth = "OCT.";    break;
                case "November":    displayedMonth = "NOV.";    break;
                case "December":    displayedMonth = "DEC.";    break;
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

            this.eventsMainAdapter.updateList(this.currentMonthDays);
        }
    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "onPause()");
        super.onPause();
    }
}