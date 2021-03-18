package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import jepara.app.kinar.R;

public class SplashScreen extends AppCompatActivity {

    String firstTime = "firstTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        int SPLASH_TIME=3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                if(!sharedpreferences.getBoolean(firstTime,false)){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(firstTime, Boolean.TRUE);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(),Onboarding.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this);
                    startActivity(intent,options.toBundle());
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this);
                    startActivity(intent,options.toBundle());
                }
                finish();
            }
        }, SPLASH_TIME);

    }
}