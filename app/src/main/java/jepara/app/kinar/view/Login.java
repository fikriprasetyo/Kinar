package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import jepara.app.kinar.R;

public class Login extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    TextView etUsername, etPassword;
    Button btnLogin, btnForgetPass;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.Nip);
        etPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.login_btn);
        btnForgetPass = findViewById(R.id.forgetPass_btn);
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan 'Back' sekali lagi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    // onClick manual
    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this);
        startActivity(intent,options.toBundle());
    }

    public void forgetPass(View view) {
        Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);
        startActivity(intent);
    }
}