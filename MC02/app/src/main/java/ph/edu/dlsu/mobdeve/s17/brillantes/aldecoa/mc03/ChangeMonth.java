package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityChangeMonthBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.StoragePreferences;

public class ChangeMonth extends AppCompatActivity {

    private ActivityChangeMonthBinding binding;
    private StoragePreferences storagePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeMonthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.storagePreferences = new StoragePreferences(this);

        switch (this.storagePreferences.getStringPreferences("currentMonth")) {
            case "January":     binding.btnChangeToJanuary.setTextColor(Color.parseColor("#000000"));   break;
            case "February":    binding.btnChangeToFebruary.setTextColor(Color.parseColor("#000000"));  break;
            case "March":       binding.btnChangeToMarch.setTextColor(Color.parseColor("#000000"));     break;
            case "April":       binding.btnChangeToApril.setTextColor(Color.parseColor("#000000"));     break;
            case "May":         binding.btnChangeToMay.setTextColor(Color.parseColor("#000000"));       break;
            case "June":        binding.btnChangeToJune.setTextColor(Color.parseColor("#000000"));      break;
            case "July":        binding.btnChangeToJuly.setTextColor(Color.parseColor("#000000"));      break;
            case "August":      binding.btnChangeToAugust.setTextColor(Color.parseColor("#000000"));    break;
            case "September":   binding.btnChangeToSeptember.setTextColor(Color.parseColor("#000000")); break;
            case "October":     binding.btnChangeToOctober.setTextColor(Color.parseColor("#000000"));   break;
            case "November":    binding.btnChangeToNovember.setTextColor(Color.parseColor("#000000"));  break;
            case "December":    binding.btnChangeToDecember.setTextColor(Color.parseColor("#000000"));  break;
        }

        binding.btnChangeToJanuary.setOnClickListener(this::onClick);
        binding.btnChangeToFebruary.setOnClickListener(this::onClick);
        binding.btnChangeToMarch.setOnClickListener(this::onClick);
        binding.btnChangeToApril.setOnClickListener(this::onClick);
        binding.btnChangeToMay.setOnClickListener(this::onClick);
        binding.btnChangeToJune.setOnClickListener(this::onClick);
        binding.btnChangeToJuly.setOnClickListener(this::onClick);
        binding.btnChangeToAugust.setOnClickListener(this::onClick);
        binding.btnChangeToSeptember.setOnClickListener(this::onClick);
        binding.btnChangeToOctober.setOnClickListener(this::onClick);
        binding.btnChangeToNovember.setOnClickListener(this::onClick);
        binding.btnChangeToDecember.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_changeToJanuary:     this.storagePreferences.saveStringPreferences("currentMonth", "January");   break;
            case R.id.btn_changeToFebruary:    this.storagePreferences.saveStringPreferences("currentMonth", "February");  break;
            case R.id.btn_changeToMarch:       this.storagePreferences.saveStringPreferences("currentMonth", "March");     break;
            case R.id.btn_changeToApril:       this.storagePreferences.saveStringPreferences("currentMonth", "April");     break;
            case R.id.btn_changeToMay:         this.storagePreferences.saveStringPreferences("currentMonth", "May");       break;
            case R.id.btn_changeToJune:        this.storagePreferences.saveStringPreferences("currentMonth", "June");      break;
            case R.id.btn_changeToJuly:        this.storagePreferences.saveStringPreferences("currentMonth", "July");      break;
            case R.id.btn_changeToAugust:      this.storagePreferences.saveStringPreferences("currentMonth", "August");    break;
            case R.id.btn_changeToSeptember:   this.storagePreferences.saveStringPreferences("currentMonth", "September"); break;
            case R.id.btn_changeToOctober:     this.storagePreferences.saveStringPreferences("currentMonth", "October");   break;
            case R.id.btn_changeToNovember:    this.storagePreferences.saveStringPreferences("currentMonth", "November");  break;
            case R.id.btn_changeToDecember:    this.storagePreferences.saveStringPreferences("currentMonth", "December");  break;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}