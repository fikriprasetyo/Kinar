package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.developer.kalert.KAlertDialog;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;
import jepara.app.kinar.R;

public class EditProfil extends AppCompatActivity {

    CircleImageView circleImageView;
    TextInputEditText namaLengkap, email, telfon, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        circleImageView = findViewById(R.id.editProfile_image);
        namaLengkap = findViewById(R.id.text_edit_nama);
        email = findViewById(R.id.text_edit_email);
        telfon = findViewById(R.id.text_edit_phone);
        alamat = findViewById(R.id.text_edit_address);

        circleImageView.setImageDrawable(getDrawable(R.drawable.photo_profil));
        namaLengkap.setText("Gemilang Kharisma Setyobudi");
        email.setText("gkharismasetyobudi@gmail.com");
        telfon.setText("085220548109");
        alamat.setText("Jl. RMP Sosrokartono No. 15 Jepara");
    }

    public void back(View view) {
        onBackPressed();
        finish();
    }

    public void save(View view) {

        //        Jika berhasil menambah Lkh
        final KAlertDialog pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
                .setTitleText("Mohon tunggu...");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(EditProfil.this,R.color.primary));
            }

            @Override
            public void onFinish() {
                pDialog.setTitleText("Berhasil Disimpan")
                        .setConfirmText("OK")
                        .confirmButtonColor(R.drawable.button_logout,getApplicationContext())
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                onBackPressed();
                                finish();
                            }
                        })
                        .changeAlertType(KAlertDialog.SUCCESS_TYPE);
            }
        }
                .start();

////        Jika gagal menambah Lkh
//        final KAlertDialog fDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
//                .setTitleText("Mohon tunggu...");
//        fDialog.show();
//        fDialog.setCancelable(false);
//        new CountDownTimer(800 * 7, 800) {
//            public void onTick(long millisUntilFinished) {
//                fDialog.getProgressHelper().setBarColor(ContextCompat.getColor(EditProfil.this,R.color.primary));
//            }
//
//            @Override
//            public void onFinish() {
//                fDialog.setTitleText("Gagal Disimpan")
//                        .setConfirmText("OK")
//                        .changeAlertType(KAlertDialog.ERROR_TYPE);
//            }
//        }
//        .start();
    }
}