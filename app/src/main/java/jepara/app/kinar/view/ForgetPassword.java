package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import jepara.app.kinar.R;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void resetnow(View view) {

    }

    public void back(View view) {
        onBackPressed();
        finish();
    }
}