package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityEditEventBinding;

public class EditEvent extends AppCompatActivity {
    private ActivityEditEventBinding binding;
    private String displayedMonth,day;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etTitle, etTime, etDetails, etAlarm, etSendTo;

        super.onCreate(savedInstanceState);
        binding = ActivityEditEventBinding.inflate(getLayoutInflater());
        binding.tvModifyTitle.setText("Edit Event");
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
        binding.llSendingTo.setVisibility(LinearLayout.GONE);
        etTitle = binding.etEditEventTitle;
        etTitle.setText(extras.getString("title"));
        etTime = binding.etEditEventTime;
        etTime.setText(extras.getString("time"));
        etDetails = binding.etEditEventDetails;
        etDetails.setText(extras.getString("details"));
        etAlarm = binding.etEditEventAlarm;
        etAlarm.setText(extras.getString("alarm"));
        etSendTo = binding.etSendingTo;

        binding.fabAddEvent.setOnClickListener(v->{
            Intent returnIntent = new Intent();
            returnIntent.putExtra("title",etTitle.getText().toString());
            returnIntent.putExtra("time",etTime.getText().toString());
            returnIntent.putExtra("details",etDetails.getText().toString());
            returnIntent.putExtra("alarm",etAlarm.getText().toString());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });





    }
}
