package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEditEventBinding;

public class AddEvent extends AppCompatActivity {
    private ActivityEditEventBinding binding;
    private String displayedMonth;
    private String day;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText tvTitle, tvTime, tvDetails, tvAlarm, tvSendTo;

        super.onCreate(savedInstanceState);
        binding = ActivityEditEventBinding.inflate(getLayoutInflater());
        binding.tvModifyTitle.setText("Add Event");
        Bundle extras = getIntent().getExtras();
        displayedMonth = extras.getString("monthname");
        day = extras.getString("day");

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
        setContentView(binding.getRoot());
        binding.tvCurrentMonth.setText(displayedMonth);
        binding.tvCurrentMonthSmall.setText(displayedMonth);
        binding.tvCurrentDay.setText(day);
        tvTitle = binding.tvEditEventTitle;
        tvTime = binding.tvEditEventTime;
        tvDetails = binding.tvEditEventDetails;
        tvAlarm = binding.tvEditEventAlarm;
        tvSendTo = binding.tvSendingTo;

        binding.fabAddEvent.setOnClickListener(v->{
            Intent returnIntent = new Intent();
            returnIntent.putExtra("title",tvTitle.getText().toString());
            returnIntent.putExtra("time",tvTime.getText().toString());
            returnIntent.putExtra("details",tvDetails.getText().toString());
            returnIntent.putExtra("alarm",tvAlarm.getText().toString());
            returnIntent.putExtra("sendto",tvSendTo.getText().toString());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });





    }
}
