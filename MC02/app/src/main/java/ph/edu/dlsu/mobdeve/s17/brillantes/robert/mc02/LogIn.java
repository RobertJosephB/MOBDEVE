package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityLogInBinding;

public class LogIn extends AppCompatActivity {

    private ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogIn.setOnClickListener( v -> {
            if (binding.etEmail.getText().toString().equals("") || binding.etPassword.getText().toString().equals("")) {
                Snackbar.make(v, "Please fill up all fields.", Snackbar.LENGTH_SHORT).show();
            } else if (!binding.etEmail.getText().toString().equals("user@gmail.com") || !binding.etPassword.getText().toString().equals("pass")) {
                Snackbar.make(v, "Incorrect Email and/or Password", Snackbar.LENGTH_SHORT).show();
            } else if (binding.etEmail.getText().toString().equals("user@gmail.com") && binding.etPassword.getText().toString().equals("pass")) {
                Intent goToMain = new Intent(LogIn.this, Events_Main.class);

                startActivity(goToMain);
                finish();
            }
        });

        binding.btnGoToSignUp.setOnClickListener( v -> {
            Intent goToSignUp = new Intent(LogIn.this, SignUp.class);
            startActivity(goToSignUp);
        });
    }
}