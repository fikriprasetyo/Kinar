package jepara.app.kinar.Fragment.Navbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import jepara.app.kinar.R;
import jepara.app.kinar.view.LayoutTerverifMonth;
import jepara.app.kinar.view.LayoutTerverifToday;
import jepara.app.kinar.view.LayoutUnverifMonth;
import jepara.app.kinar.view.LayoutUnverifToday;


public class FragmentHome extends Fragment {

    Toolbar toolbar;
    private SwipeRefreshLayout SwipeRefresh;
    CardView cardViewTT, cardViewUT, cardViewTM, cardViewUM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = view.findViewById(R.id.toolbarHome);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //find cardview today
        cardViewTT = view.findViewById(R.id.cardView_terverif_today);
        cardViewUT = view.findViewById(R.id.cardView_unverif_today);

        cardViewTT.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LayoutTerverifToday.class);
            startActivity(intent);
        });

        cardViewUT.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LayoutUnverifToday.class);
            startActivity(intent);
        });

        //find cardview monthly
        cardViewTM = view.findViewById(R.id.cardView_terverif_month);
        cardViewUM = view.findViewById(R.id.cardView_unverif_month);

        cardViewTM.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LayoutTerverifMonth.class);
            startActivity(intent);
        });

        cardViewUM.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LayoutUnverifMonth.class);
            startActivity(intent);
        });


        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = view.findViewById(R.id.refreshHome);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.primary);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        SwipeRefresh.setOnRefreshListener(() -> {
            // Handler digunakan untuk menjalankan jeda selama 5 detik
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                // Berhenti berputar/refreshing
                SwipeRefresh.setRefreshing(false);
            },4000); //4000 millisecond = 4 detik
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}