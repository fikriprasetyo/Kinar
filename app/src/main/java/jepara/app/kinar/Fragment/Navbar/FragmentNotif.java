package jepara.app.kinar.Fragment.Navbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import jepara.app.kinar.Adapter.RecyclerviewAdapterNotif;
import jepara.app.kinar.R;
import jepara.app.kinar.model.model_notif;


public class FragmentNotif extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerviewAdapterNotif adapter;
    private ArrayList<model_notif> NotifArrayList;
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
        View view = inflater.inflate(R.layout.fragment_notif, container, false);

        toolbar = view.findViewById(R.id.toolbarNotif);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = view.findViewById(R.id.refreshNotif);

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

        recyclerView = view.findViewById(R.id.recyclerview_notif);
        recyclerView.setHasFixedSize(true);

        addData();
        adapter = new RecyclerviewAdapterNotif(NotifArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;

    }

    private void addData() {
        NotifArrayList = new ArrayList<>();
        NotifArrayList.add(new model_notif("Terverifikasi", "Kinar / Kinerja Aparatur adalah Aplikasi untuk menilai kinerja PNS di Kabupaten Jepara Sebagai syarat penerimaan TPP (Tunjangan Penghasilan Pegawai) bagi PNS.", "02/02/2021"));
        NotifArrayList.add(new model_notif("Terverifikasi 1", "Kinar / Kinerja Aparatur adalah Aplikasi untuk menilai kinerja PNS di Kabupaten Jepara", "03/02/2021"));
        NotifArrayList.add(new model_notif("Terverifikasi 2", "Sebagai syarat penerimaan TPP (Tunjangan Penghasilan Pegawai) bagi PNS.", "04/02/2021"));
        NotifArrayList.add(new model_notif("Terverifikasi 3", "Kinar / Kinerja Aparatur adalah Aplikasi untuk menilai kinerja PNS", "05/02/2021"));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notifikasi_toolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filterNotif) {
            Toast.makeText(getActivity(), "Filter Notifikasi", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}