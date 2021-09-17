package ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.UserDAO;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.dao.UserDAOFirebaseImpl;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.databinding.ActivitySignUp2Binding;
import ph.edu.dlsu.mobdeve.s17.brillantes.aldecoa.mc03.models.UserModel;

public class SignUp2 extends AppCompatActivity {

    private ActivitySignUp2Binding binding;
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userDAO = new UserDAOFirebaseImpl(getApplicationContext());
        UserModel temp = new UserModel();
        temp.setUserEmail("email");
        temp.setUserPassword("pass");
        userDAO.addUser(temp);

        Bundle bundle = getIntent().getExtras();

        binding.btnFinishSignup.setOnClickListener( v -> {
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
                        UserModel temp = new UserModel();
                        temp.setUserEmail(bundle.getString("email"));
                        temp.setUserPassword(bundle.getString("password"));
                        userDAO.addUser(temp);
                        startActivity(finishSignUp);
                        finish();
                    }
                }.start();
            }
        });
    }
}