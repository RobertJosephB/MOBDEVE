package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityEditEventBinding;

public class AddEvent extends AppCompatActivity {
    private ActivityEditEventBinding binding;
    private String displayedMonth;
    private String day;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etTitle, etTimeHr,etTimeMin, etDetails, etSendTo;
        ToggleButton btnAlarm,btnEmail;

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
        etTitle = binding.etEditEventTitle;
        etTimeHr = binding.etEditEventTimeHr;
        etTimeMin = binding.etEditEventTimeMin;
        etDetails = binding.etEditEventDetails;
        btnAlarm = binding.btnEditEventAlarm;
        btnEmail = binding.btnEditEventEmail;
        String alarmSettings = extras.getString("alarm");
        if(alarmSettings!=null)
        switch (alarmSettings) {
            case "alarmemail":
                btnAlarm.setChecked(true);
                btnEmail.setChecked(true);
                break;
            case "alarm":
                btnAlarm.setChecked(true);
                btnEmail.setChecked(false);
                break;
            case "email":
                btnAlarm.setChecked(false);
                btnEmail.setChecked(true);
                break;
            default:
                btnAlarm.setChecked(false);
                btnEmail.setChecked(false);
                break;
        }
        etSendTo = binding.etSendingTo;

        binding.fabAddEvent.setOnClickListener(v->{
            Intent returnIntent = new Intent();
            returnIntent.putExtra("title",etTitle.getText().toString());
            returnIntent.putExtra("timeHr",etTimeHr.getText().toString());
            returnIntent.putExtra("timeMin",etTimeMin.getText().toString());
            returnIntent.putExtra("details",etDetails.getText().toString());
            if(btnAlarm.isChecked() && btnEmail.isChecked())
                returnIntent.putExtra("alarm","alarmemail");
            else if(btnAlarm.isChecked())
                returnIntent.putExtra("alarm","alarm");
            else if(btnEmail.isChecked())
                returnIntent.putExtra("alarm","email");
            else
                returnIntent.putExtra("alarm","");
            returnIntent.putExtra("sendto",etSendTo.getText().toString());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });





    }
}
