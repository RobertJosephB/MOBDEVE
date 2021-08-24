package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.adapters.EventAdapter;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEventsDayspecificBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class Events_DaySpecific extends AppCompatActivity {

    private ActivityEventsDayspecificBinding binding;
    private ArrayList<EventModel> eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventsDayspecificBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // dummy data
        init();

        eventAdapter = new EventAdapter(getApplicationContext(), eventList);
        binding.rvEventList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvEventList.setAdapter(eventAdapter);
    }
    // /*
    private void init() {
        eventList = new ArrayList<>();

        eventList.add(new EventModel(
                "1",
                "Activity 1",
                "7:30 am",
                "This is Activity 1",
                "Email",
                "15 minutes before"
        ));

        eventList.add(new EventModel(
                "1",
                "Activity 2",
                "9:30 am",
                "This is Activity 2",
                "Alarm",
                "15 minutes before"
        ));
    }
    // */
}
