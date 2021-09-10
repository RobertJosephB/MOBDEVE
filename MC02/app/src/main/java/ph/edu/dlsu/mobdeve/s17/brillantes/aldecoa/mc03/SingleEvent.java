package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOFirebaseImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.EventDAOSQLImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivitySingleEventBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.EventModel;

public class SingleEvent extends AppCompatActivity {
    private ActivitySingleEventBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String newTitle = "",newTime= "", newDetails= "",newAlarm= "";
    private EventDAO eventDAO;


    @SuppressLint("SetTextI18n")
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
        String userID = extras.getString("userID");
        int id = extras.getInt("id");
        binding.tvCurrentMonthSmall.setText(displayedMonth);
        eventDAO = new EventDAOFirebaseImpl(getApplicationContext(),userID);

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
                    newTime = data.getString("timeHr").concat(data.getString("timeMin"));
                    newDetails = data.getString("details");
                    newAlarm = data.getString("alarm");




                    EventModel temp = eventDAO.getEvent(id);
                    new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            temp.setEventTitle(newTitle);
                            if(newTime.length() == 3)
                                temp.setTime("0"+newTime);
                            else
                                temp.setTime(newTime);
                            temp.setDetails(newDetails);
                            temp.setNotificationType(newAlarm);
                        }
                    }.start();





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
        if(title==null)
        {
            binding.tvEventTitle.setText("WHOLE DAY");
        }
        binding.tvNotifyType.setText(alarm);
        binding.tvTime.setText(time);


        setContentView(binding.getRoot());



        binding.fabAddEvent.setOnClickListener( v -> {

            Intent addEvent = new Intent(SingleEvent.this, EditEvent.class);
            addEvent.putExtra("monthname",extras.getString("monthname"));
            addEvent.putExtra("day",day);
            addEvent.putExtra("title",title);
            addEvent.putExtra("time",time);
            addEvent.putExtra("details",details);
            addEvent.putExtra("alarm",alarm);


            activityResultLauncher.launch(addEvent);



        });

    }
}
