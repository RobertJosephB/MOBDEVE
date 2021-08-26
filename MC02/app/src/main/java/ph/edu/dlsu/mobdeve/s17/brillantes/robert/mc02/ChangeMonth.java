package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters.ChangeMonthAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityChangeMonthBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.CalendarModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.MonthModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.YearModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.util.StoragePreferences;

public class ChangeMonth extends AppCompatActivity {

    private ActivityChangeMonthBinding binding;
    private CalendarModel calendar;
    private ChangeMonthAdapter changeMonthAdapter;
    private ArrayList<String> monthList;
    private StoragePreferences storagePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeMonthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.storagePreferences = new StoragePreferences(this);
        this.calendar = (CalendarModel) getIntent().getSerializableExtra("calendar");

        int i;
        int currentYearIndex = -1;

        for (i = 0; i < this.calendar.getYears().size(); i++) {
            if (this.calendar.getYears().get(i).getYearNum().equals(this.storagePreferences.getStringPreferences("currentYear"))) {
                currentYearIndex = i;
            }
        }

        YearModel tempCurrentYearModel = this.calendar.getYears().get(currentYearIndex);

        this.monthList = new ArrayList<>();

        for (i = 0; i < tempCurrentYearModel.getMonths().size(); i++) {
            this.monthList.add(tempCurrentYearModel.getMonths().get(i).getMonthName());
        }

        changeMonthAdapter = new ChangeMonthAdapter(getApplicationContext(), this.monthList, this.storagePreferences.getStringPreferences("currentMonth"));
        binding.rvChangeMonthList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvChangeMonthList.setAdapter(changeMonthAdapter);

    }
}