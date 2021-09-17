package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}