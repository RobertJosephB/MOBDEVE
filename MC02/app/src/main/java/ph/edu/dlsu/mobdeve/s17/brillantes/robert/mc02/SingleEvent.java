package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.dao.EventDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivitySingleEventBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.models.EventModel;

public class SingleEvent extends AppCompatActivity {
    private ActivitySingleEventBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String newTitle = "",newTime= "", newDetails= "",newAlarm= "";
    private EventDAO eventDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySingleEventBinding.inflate(getLayoutInflater());
        Bundle extras = getIntent().getExtras();
        String displayedMonth = extras.getString("monthname");
        String day = extras.getString("day");
        String title = extras.getString("title");
        String time = extras.getString("time");
        String details = extras.getString("details");
        String alarm = extras.getString("alarm");
        int id = extras.getInt("id");
        binding.tvCurrentMonthSmall.setText(displayedMonth);
        eventDAO = new EventDAOSQLImpl(getApplicationContext());

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

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                    Bundle data = result.getData().getExtras();
                    newTitle = data.getString("title");
                    newTime = data.getString("time");
                    newDetails = data.getString("details");
                    newAlarm = data.getString("alarm");




                    EventModel temp = eventDAO.getEvent(id);


                    temp.setEventTitle(newTitle);
                    temp.setTime(newTime);
                    temp.setDetails(newDetails);
                    temp.setNotificationType(newAlarm);



                    eventDAO.updateEvent(temp);

                    binding.tvDetails.setText(newDetails);
                    binding.tvEventTitle.setText(newTitle);
                    binding.tvNotifyType.setText(newAlarm);
                    binding.tvTime.setText(newTime);



                }
            }
        });



        binding.tvCurrentMonth.setText(displayedMonth);
        binding.tvCurrentDay.setText(day);
        binding.tvDetails.setText(details);
        binding.tvEventTitle.setText(title);
        binding.tvNotifyType.setText(alarm);
        binding.tvTime.setText(time);


        setContentView(binding.getRoot());



        binding.fabAddEvent.setOnClickListener( v -> {

            Intent addEvent = new Intent(SingleEvent.this, EditEvent.class);
            addEvent.putExtra("monthname",extras.getString("monthname"));
            addEvent.putExtra("day",day);

            activityResultLauncher.launch(addEvent);



        });

    }
}
