package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.UserDAOFirebaseImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivityLogInBinding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.UserModel;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.util.StoragePreferences;

public class LogIn extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private StoragePreferences storagePreferences;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userDAO = new UserDAOFirebaseImpl(getApplicationContext());

        ArrayList<UserModel> users = userDAO.getUsers();

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                binding.btnLogIn.setOnClickListener( v -> {
                    System.out.println(users.size());
                    for (int i = 0; i < users.size(); i++) {

                        if (binding.etEmail.getText().toString().equals("") || binding.etPassword.getText().toString().equals("")) {
                            Snackbar.make(v, "Please fill up all fields.", Snackbar.LENGTH_SHORT).show();
                        } else if (!binding.etEmail.getText().toString().equals(users.get(i).getUserEmail()) || !binding.etPassword.getText().toString().equals(users.get(i).getUserPassword()) && i==users.size()-1) {
                            Snackbar.make(v, "Incorrect Email and/or Password", Snackbar.LENGTH_SHORT).show();
                        } else if (binding.etEmail.getText().toString().equals(users.get(i).getUserEmail()) && binding.etPassword.getText().toString().equals(users.get(i).getUserPassword())) {
                            Intent goToMain = new Intent(LogIn.this, Events_Main.class);


                            goToMain.putExtra("userID", users.get(i).getUserKey());

                            startActivity(goToMain);
                            finish();
                        }
                    }
                });
            }
        }.start();



        binding.btnGoToSignUp.setOnClickListener( v -> {
            Intent goToSignUp = new Intent(LogIn.this, SignUp.class);
            startActivity(goToSignUp);
        });
    }
}