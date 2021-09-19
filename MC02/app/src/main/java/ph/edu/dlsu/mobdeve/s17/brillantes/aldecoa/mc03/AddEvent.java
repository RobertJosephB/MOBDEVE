package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityEditEventBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.NotifyReceiver;

public class AddEvent extends AppCompatActivity {
    private ActivityEditEventBinding binding;
    private String displayedMonth;
    private String day;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar cal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etTitle, etTimeHr,etTimeMin, etDetails;
        ToggleButton btnAlarm,btnEmail;

        super.onCreate(savedInstanceState);
        binding = ActivityEditEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvModifyTitle.setText("Add Event");
        Bundle extras = getIntent().getExtras();
        String email = extras.getString("email");
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
        if(email.length()>=6) {
            binding.tvName.setText(email.substring(0, 6));
        }
        else
            binding.tvName.setText(email);
        binding.tvCurrentMonth.setText(displayedMonth);
        binding.tvCurrentMonthSmall.setText(displayedMonth);
        binding.tvCurrentDay.setText(day);
        binding.btnEditEventAlarm.setChecked(true);
        binding.btnEditEventEmail.setChecked(true);
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
        binding.btnEditEventAlarm.setOnClickListener(v -> {
            if(!binding.btnEditEventAlarm.isChecked())
                binding.llAlarmTime.setVisibility(View.INVISIBLE);
            else
                binding.llAlarmTime.setVisibility(View.VISIBLE);
        });


        binding.logoutEe.setOnClickListener( v -> {
            Intent welcome = new Intent(AddEvent.this, Welcome.class);

            startActivity(welcome);
            finish();
        });

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
            setResult(Activity.RESULT_OK,returnIntent);


            if(binding.btnEditEventAlarm.isChecked()) {
                 createNotificationChannel();


                int hour = Integer.parseInt(binding.etEditAlarmTimeHr.getText().toString());
                int minute = Integer.parseInt(binding.etEditAlarmTimeMin.getText().toString());
                int day = Integer.parseInt(this.day);
                int year = Integer.parseInt("2021");

                cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, day);

                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(AddEvent.this, NotifyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);




            }


            if(binding.btnEditEventEmail.isChecked()) {
                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL, new String[]{});
                send.putExtra(Intent.EXTRA_SUBJECT, etTitle.getText().toString().trim());
                send.putExtra(Intent.EXTRA_TEXT, "Time: " + etTimeHr.getText().toString() + ":" + etTimeMin.getText().toString() + "\nDetails: " + etDetails.getText().toString().trim());

                send.setType("message/rfc822");
                startActivity(Intent.createChooser(send, "Send To:"));


            }
            finish();

        });
    }

    /*
    private void setAlarm() {
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AddEvent.this, NotifyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0, intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(this,"Alarm Set Successfully",Toast.LENGTH_SHORT).show();
    }
    */

    /*
    private class SendMail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = ProgressDialog.show(AddEvent.this,"Please Wait","Sending Mail...",true,false);
        }
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "SUCCESS";
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return "ERROR";
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            progressDialog.dismiss();
            if(s.equals("SUCCESS")){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddEvent.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#98c4fc'>SUCCESS</font>"));
                builder.setMessage("Mail Sent");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }




        }
    }
    */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "alarmChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("alarm", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}


