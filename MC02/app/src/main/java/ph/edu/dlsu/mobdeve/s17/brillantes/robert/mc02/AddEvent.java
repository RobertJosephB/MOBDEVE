package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityLogInBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.util.StoragePreferences;

public class AddEvent extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private StoragePreferences storagePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogIn.setOnClickListener( v -> {

        });

        binding.btnGoToSignUp.setOnClickListener( v -> {
            Intent goToSignUp = new Intent(AddEvent.this, SignUp.class);
            startActivity(goToSignUp);
        });
    }
}