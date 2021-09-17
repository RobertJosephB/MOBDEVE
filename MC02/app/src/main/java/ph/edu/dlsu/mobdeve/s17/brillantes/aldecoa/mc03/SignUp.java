package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnGoToSignUp2.setOnClickListener( v -> {
            Intent goToSignUp2 = new Intent(SignUp.this, SignUp2.class);
            Bundle bundle = new Bundle();
            String su_email = binding.etSUEmail.getText().toString();
            String su_password = binding.etSUPassword.getText().toString();
            String su_confirmPassword = binding.etSUConfirmPassword.getText().toString();

            if (su_email.equals("")) {
                Snackbar.make(v, "Email field is empty.", Snackbar.LENGTH_SHORT).show();
            } else if (su_password.equals("") && su_confirmPassword.equals("")) {
                Snackbar.make(v, "Please fill up all fields.", Snackbar.LENGTH_SHORT).show();
            } else {
                if (su_password.equals(su_confirmPassword)) {
                    bundle.putString("email", su_email);
                    bundle.putString("password", su_password);

                    goToSignUp2.putExtras(bundle);

                    startActivity(goToSignUp2);
                    finish();
                } else {
                    Snackbar.make(v, "Passwords do no match.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnReturnToLogIn.setOnClickListener( v -> {
            finish();
        });
    }
}