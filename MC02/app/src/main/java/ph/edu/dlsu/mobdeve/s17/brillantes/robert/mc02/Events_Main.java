package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters.EventsMainAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEventsMainBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.CalendarModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.DayModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.MonthModel;

public class Events_Main extends AppCompatActivity {

    private ActivityEventsMainBinding binding;
    private CalendarModel calendar;
    private EventsMainAdapter eventsMainAdapter;
    private ArrayList<DayModel> currentMonthDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventsMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendar = new CalendarModel();

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

        for (i = 0; i < calendar.getCalendar().size(); i++) {
            if (calendar.getCalendar().get(i).getYearNum().equals(dNowYear))
                currentYearIndex = i;
        }

        displayedYear = calendar.getCalendar().get(currentYearIndex).getYearNum();

        ArrayList<MonthModel> tempCurrentYear = calendar.getCalendar().get(currentYearIndex).getMonths();

        for (i = 0; i < tempCurrentYear.size(); i++) {
            if (tempCurrentYear.get(i).getMonthNum().equals(dNowMonth))
                currentMonthIndex = i;
        }

        displayedMonth = tempCurrentYear.get(currentMonthIndex).getMonthName();

        switch (displayedMonth) {
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

        this.currentMonthDays = tempCurrentYear.get(currentMonthIndex).getDays();

        binding.tvCurrentMonth.setText(displayedMonth);
        binding.tvCurrentYear.setText(displayedYear);

        eventsMainAdapter = new EventsMainAdapter(getApplicationContext(), this.currentMonthDays);
        binding.rvEventMainList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventMainList.setAdapter(eventsMainAdapter);
    }
}