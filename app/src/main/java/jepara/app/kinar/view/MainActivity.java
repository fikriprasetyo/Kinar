package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import jepara.app.kinar.Fragment.Navbar.FragmentEkinerja;
import jepara.app.kinar.Fragment.Navbar.FragmentHome;
import jepara.app.kinar.Fragment.Navbar.FragmentNotif;
import jepara.app.kinar.Fragment.Navbar.FragmentProfil;
import jepara.app.kinar.Fragment.Navbar.TambahLkh;
import jepara.app.kinar.R;

public class MainActivity extends AppCompatActivity {

    private FragmentHome fragmentHome;
    private FragmentEkinerja fragmentEkinerja;
    private TambahLkh tambahLkh;
    private FragmentNotif fragmentNotif;
    private FragmentProfil fragmentProfil;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // blm tahu
        MultiDex.install(this);

        //
        fragmentHome = new FragmentHome();
        fragmentEkinerja = new FragmentEkinerja();
        tambahLkh = new TambahLkh();
        fragmentNotif = new FragmentNotif();
        fragmentProfil = new FragmentProfil();

        // set MainActivity sebagai FragmentHome
        setFragment(fragmentHome);

        // set bottom navbar
        SpaceNavigationView spaceNavigationView = findViewById(R.id.bottom_navbar);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        // set lihat icon aja
        spaceNavigationView.showIconOnly();

        // set tombol tengah
        spaceNavigationView.changeCenterButtonIcon(R.drawable.ic_add);

        // set tombol lain
        spaceNavigationView.addSpaceItem(new SpaceItem("Home", R.drawable.ic_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("Notifikasi", R.drawable.ic_notif));
        spaceNavigationView.addSpaceItem(new SpaceItem("E-Kinerja", R.drawable.ic_ekinerja));
        spaceNavigationView.addSpaceItem(new SpaceItem("Profil", R.drawable.ic_user));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(getApplicationContext(),TambahLkh.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(intent,options.toBundle());
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex){
                    case 0:
                        setFragment(fragmentHome);
                        return;
                    case 1:
                        setFragment(fragmentNotif);
                        return;
                    case 2:
                        setFragment(fragmentEkinerja);
                        return;
                    case 3:
                        setFragment(fragmentProfil);
                        return;
                    default:
                        setFragment(fragmentHome);
                        return;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) { }
        });
    }

    // layout tempat taruh fragmentnya
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

    @Override
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
}