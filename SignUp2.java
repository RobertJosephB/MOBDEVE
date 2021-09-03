package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivitySignUp2Binding;

public class SignUp2 extends AppCompatActivity {

    private ActivitySignUp2Binding binding;
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUp2.this);
            }
        });

        Bundle bundle = getIntent().getExtras();

        binding.btnFinishSignUp.setOnClickListener( v -> {
            Intent finishSignUp = new Intent(SignUp2.this, LogIn.class);
            String su_firstName = binding.etSUFirstName.getText().toString();
            String su_lastName = binding.etSULastName.getText().toString();

            if (su_firstName.equals("") || su_lastName.equals("")){
                Snackbar.make(v, "Please fill up all fields.", Snackbar.LENGTH_SHORT).show();
            } else {
                bundle.putString("firstName", su_firstName);
                bundle.putString("lastName", su_lastName);

                Snackbar.make(v, "Registered! Welcome, " + bundle.getString("firstName") + "!", Snackbar.LENGTH_SHORT).show();

                new CountDownTimer(2000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        startActivity(finishSignUp);
                        finish();
                    }
                }.start();
            }
        });
    }
}