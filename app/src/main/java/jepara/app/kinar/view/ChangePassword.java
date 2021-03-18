package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.developer.kalert.KAlertDialog;

import jepara.app.kinar.R;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void back(View view) {
        onBackPressed();
        finish();
    }

    public void resetnow(View view) {

        //        Jika berhasil merubah password
        final KAlertDialog pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
                .setTitleText("Mohon tunggu...");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(ChangePassword.this,R.color.primary));
            }

            @Override
            public void onFinish() {
                pDialog.setTitleText("Password berhasil diganti")
                        .setConfirmText("OK")
                        .confirmButtonColor(R.drawable.button_logout,getApplicationContext())
                        .setConfirmClickListener(kAlertDialog -> {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        })
                        .changeAlertType(KAlertDialog.SUCCESS_TYPE);
            }
        }
                .start();

////        Jika gagal merubah password
//        final KAlertDialog fDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
//                .setTitleText("Mohon tunggu...");
//        fDialog.show();
//        fDialog.setCancelable(false);
//        new CountDownTimer(800 * 7, 800) {
//            public void onTick(long millisUntilFinished) {
//                fDialog.getProgressHelper().setBarColor(ContextCompat.getColor(ChangePassword.this,R.color.primary));
//            }
//
//            @Override
//            public void onFinish() {
//                fDialog.setTitleText("Password Gagal diganti")
//                        .setConfirmText("OK")
//                        .changeAlertType(KAlertDialog.ERROR_TYPE);
//            }
//        }
//                .start();
    }
}