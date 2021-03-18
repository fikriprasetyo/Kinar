package jepara.app.kinar.Fragment.Navbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.kalert.KAlertDialog;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import jepara.app.kinar.R;
import jepara.app.kinar.view.About;
import jepara.app.kinar.view.ChangePassword;
import jepara.app.kinar.view.EditProfil;
import jepara.app.kinar.view.Login;


public class FragmentProfil extends Fragment {

    TextView namaLengkap, email, telfon, alamat;
    CircleImageView circleImageView;
    private SwipeRefreshLayout SwipeRefresh;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        toolbar = view.findViewById(R.id.toolbarProfil);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = view.findViewById(R.id.refreshProfil);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.primary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler digunakan untuk menjalankan jeda selama 5 detik
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Berhenti berputar/refreshing
                        SwipeRefresh.setRefreshing(false);
                    }
                },4000); //4000 millisecond = 4 detik
            }
        });


        circleImageView = view.findViewById(R.id.imageView);
        namaLengkap = view.findViewById(R.id.textNama);
        email = view.findViewById(R.id.text_email);
        telfon = view.findViewById(R.id.text_telepon);
        alamat = view.findViewById(R.id.text_alamat);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.profil_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case (R.id.more_edit):
                intent = new Intent(getActivity(), EditProfil.class);
                startActivity(intent);
                break;
            case (R.id.more_changepass):
                intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
                break;
            case (R.id.more_about):
                intent = new Intent(getActivity(), About.class);
                startActivity(intent);
                break;
            case (R.id.more_Logout):
                new KAlertDialog(this.getActivity(), KAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Apakah anda ngin Logout ?")
                        .setTitleTextSize(16)
                        .setCustomImage(R.drawable.ic_logout, this.getActivity())
                        .setContentText("\n")
                        .cancelButtonColor(R.drawable.button_cancel,this.getActivity())

                        .setCancelText("Batal")
                        // aksi saat BATAL LOGOUT
//                      .setCancelClickListener(new KAlertDialog.KAlertClickListener() {
//                        @Override
//                        public void onClick(KAlertDialog kAlertDialog) {
//                            // do nothing
//                        }
//                    })

                        .setConfirmText("Logout")
                        .confirmButtonColor(R.drawable.button_logout,this.getActivity())
                        .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                            @Override
                            public void onClick(KAlertDialog kAlertDialog) {
                                final KAlertDialog pDialog = new KAlertDialog(getActivity(), KAlertDialog.PROGRESS_TYPE)
                                        .setTitleText("Sampai jumpa...");
                                pDialog.show();
                                new CountDownTimer(800 * 7, 800) {
                                    public void onTick(long millisUntilFinished) {
                                        pDialog.getProgressHelper().setBarColor(ContextCompat.getColor(getActivity(),R.color.blue_btn_bg_color));
                                    }

                                    @Override
                                    public void onFinish() {
                                        Intent intent = new Intent(getActivity(), Login.class);
                                        startActivity(intent);
                                    }
                                }.start();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}