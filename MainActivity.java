package ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ph.edu.dlsu.mobdeve.s17.brillantes.robert.mc02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}