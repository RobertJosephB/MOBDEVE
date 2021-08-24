package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityWelcomeBinding;

public class Welcome extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent goToLogIn = new Intent(Welcome.this, LogIn.class);

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(goToLogIn);
                finish();
            }
        }.start();
    }
}