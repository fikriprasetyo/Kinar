package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.developer.kalert.KAlertDialog;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import jepara.app.kinar.R;

public class EditLkh extends AppCompatActivity {

    ImageButton back;
    Button btn_editlkh;
    private DatePickerDialog datePickerDialog;
    private Button btn_datePicker, btn_timePickerStart, btn_timePickerFinish;
    private TimePickerDialog timePickerDialog;
    private Spinner spActivity, spLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lkh);

        //date
        initDatePicker();
        btn_datePicker = findViewById(R.id.datePickerButton);
        btn_datePicker.setText(getTodaysDate());

        //time start
        btn_timePickerStart = (Button) findViewById(R.id.timePicker_mulai);
        //time finish
        btn_timePickerFinish = (Button) findViewById(R.id.timePicker_selesai);

        //activity
        spActivity = findViewById(R.id.sp_aktifitas);
        spLocation = findViewById(R.id.sp_location);

        ArrayList<String> activitylist = new ArrayList<>();
        ArrayList<String> locationlist = new ArrayList<>();

        activitylist.add("Aktifitas");
        activitylist.add("Apel");
        activitylist.add("Menyusun Produk Hukum (rerda)");
        activitylist.add("Merumuskan");
        activitylist.add("Menjadi Kuasa Hukum");
        activitylist.add("Menyusun Perjanjian/MoU");
        activitylist.add("Menyusun Produk Hukum(Sk/intruksi)");
        activitylist.add("Menyusun Produk Hukum(Perbub)");
        activitylist.add("Mengoreksi Keputusan/Perjanjian/MoU");
        activitylist.add("Mengoreksi Produk Hukum (SK/Instruksi)");
        activitylist.add("Mengoreksi Produk Hukum (Perbub)");
        activitylist.add("Mengoreksi Produk Hukum (Perda)");
        activitylist.add("Menerima Konsultasi");
        activitylist.add("Melaksanakan Tugas Lain");
        activitylist.add("MEnghubungi");
        activitylist.add("Meliput");
        activitylist.add("Mewawancarai");
        activitylist.add("Mem-Fotocopy");

        locationlist.add("Tempat");
        locationlist.add("Apel");
        locationlist.add("Menyusun Produk Hukum (rerda)");
        locationlist.add("Merumuskan");
        locationlist.add("Menjadi Kuasa Hukum");
        locationlist.add("Menyusun Perjanjian/MoU");
        locationlist.add("Menyusun Produk Hukum(Sk/intruksi)");
        locationlist.add("Menyusun Produk Hukum(Perbub)");
        locationlist.add("Mengoreksi Keputusan/Perjanjian/MoU");
        locationlist.add("Mengoreksi Produk Hukum (SK/Instruksi)");
        locationlist.add("Mengoreksi Produk Hukum (Perbub)");
        locationlist.add("Mengoreksi Produk Hukum (Perda)");
        locationlist.add("Menerima Konsultasi");
        locationlist.add("Melaksanakan Tugas Lain");
        locationlist.add("MEnghubungi");
        locationlist.add("Meliput");
        locationlist.add("Mewawancarai");
        locationlist.add("Mem-Fotocopy");

        spActivity.setAdapter(new ArrayAdapter<>(EditLkh.this, android.R.layout.simple_spinner_dropdown_item,activitylist));
        spLocation.setAdapter(new ArrayAdapter<>(EditLkh.this, android.R.layout.simple_spinner_dropdown_item,locationlist));

        spActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    //show
                }
                else {
                    String sActivity = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    //show
                }
                else {
                    String sLocation = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //other btn
        back = findViewById(R.id.back);
        btn_editlkh = findViewById(R.id.tambahlkh_btn);

        btn_timePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog("timeStart");
            }
        });

        btn_timePickerFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog("timeFinish");
            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(TambahLkh.this, MainActivity.class));
//            }
//        });
//
//        btn_tambahlkh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    //    Date
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Januari";
        if(month == 2)
            return "Februari";
        if(month == 3)
            return "Maret";
        if(month == 4)
            return "April";
        if(month == 5)
            return "Mei";
        if(month == 6)
            return "Juni";
        if(month == 7)
            return "Juli";
        if(month == 8)
            return "Agustus";
        if(month == 9)
            return "September";
        if(month == 10)
            return "Oktober";
        if(month == 11)
            return "November";
        if(month == 12)
            return "Desember";

        //default should never happen
        return "Januari";
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                btn_datePicker.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void showTimeDialog(String timetype) {
        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */

                switch (timetype){
                    case "timeStart":
                        String timeStart = makeTimeString(hourOfDay, minute);
                        btn_timePickerStart.setText(timeStart);
                        break;

                    case "timeFinish":
                        String timeFinish = makeTimeString(hourOfDay, minute);
                        btn_timePickerFinish.setText(timeFinish);
                        break;
                }
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }

    private String makeTimeString(int hourOfDay, int minute) {
        return +hourOfDay+":"+minute;
    }

    public void back(View view) {
        onBackPressed();
        finish();
    }

    public void simpan(View view) {
        //        Jika berhasil menambah Lkh
        final KAlertDialog pDialog = new KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
                .setTitleText("Mohon tunggu...");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(EditLkh.this,R.color.primary));
            }

            @Override
            public void onFinish() {
                pDialog.setTitleText("Perubahan berhasil Disimpan")
                        .setConfirmText("OK")
                        .confirmButtonColor(R.drawable.button_logout,getApplicationContext())
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
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
//                fDialog.getProgressHelper().setBarColor(ContextCompat.getColor(EditLkh.this,R.color.primary));
//            }
//
//            @Override
//            public void onFinish() {
//                fDialog.setTitleText("Perubahan gagal Disimpan")
//                        .setConfirmText("OK")
//                        .changeAlertType(KAlertDialog.ERROR_TYPE);
//            }
//        }
//        .start();
    }
}