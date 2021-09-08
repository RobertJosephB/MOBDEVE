package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityLogInBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.StoragePreferences;

public class LogIn extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private StoragePreferences storagePreferences;

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