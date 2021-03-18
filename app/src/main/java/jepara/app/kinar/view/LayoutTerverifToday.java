package jepara.app.kinar.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.developer.kalert.KAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jepara.app.kinar.Adapter.ExpandableListAdapterTerverifToday;
import jepara.app.kinar.R;
import jepara.app.kinar.model.content_terveriftoday;

public class LayoutTerverifToday extends AppCompatActivity {

    private Context context;
    private ExpandableListView list_view;
    List<String> listDataParent;
    HashMap<String, List<content_terveriftoday>> listDataChild;
    private SwipeRefreshLayout SwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_terverif_today);

        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = findViewById(R.id.refresh_terveriftoday);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        SwipeRefresh.setColorSchemeResources(R.color.green);

        // Mengeset listener yang akan dijalankan saat layar di refresh/swipe
        SwipeRefresh.setOnRefreshListener(() -> {
            // Handler digunakan untuk menjalankan jeda selama 5 detik
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                // Berhenti berputar/refreshing
                SwipeRefresh.setRefreshing(false);
            },4000); //4000 millisecond = 4 detik
        });

        this.context = this.getApplicationContext();
        list_view = (ExpandableListView) findViewById(R.id.list_view);
        createListData();
        registerForContextMenu(list_view);
        // Listview Group click listener
        list_view.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // TODO GroupClickListener work
                return false;
            }
        });

        // Listview Group expanded listener
        list_view.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO GroupExpandListener work
            }
        });

        // Listview Group collasped listener
        list_view.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // TODO GroupCollapseListener work
            }
        });

        // Listview on child click listener
        list_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // action when clicked
                return false;
            }
        });

        // Refresh not work when scroll up the list view
        list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                SwipeRefresh.setEnabled(firstVisibleItem == 0);
            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.option_ekinerja,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Intent intent = new Intent(getApplicationContext(), EditLkh.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                Toast.makeText(getApplicationContext(),"LKH Terhapus",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createListData() {
        listDataParent = new ArrayList<String>();
        listDataChild = new HashMap<String, List<content_terveriftoday>>();

        // Adding child data
        listDataParent.add("Kamis, 4 Februari 2021");
        listDataParent.add("Selasa, 2 Februari 2021");
        listDataParent.add("Senin, 1 Februari 2021");

        // Adding child data List one
        List<content_terveriftoday> tanggal3 = new ArrayList<content_terveriftoday>();
        tanggal3.add(new content_terveriftoday("Jam Mulai","07:00"));
        tanggal3.add(new content_terveriftoday("Jam Selesai","08:00"));
        tanggal3.add(new content_terveriftoday("Aktifitas","Apel"));
        tanggal3.add(new content_terveriftoday("Poin","50"));
        tanggal3.add(new content_terveriftoday("Tempat","Halaman Kantor"));
        tanggal3.add(new content_terveriftoday("Keterangan","Apel Pagi"));
        tanggal3.add(new content_terveriftoday("Status","Terverifikasi"));

        // Adding child data List two
        List<content_terveriftoday> tanggal2 = new ArrayList<content_terveriftoday>();
        tanggal2.add(new content_terveriftoday("Jam Mulai","07:00"));
        tanggal2.add(new content_terveriftoday("Jam Selesai","08:00"));
        tanggal2.add(new content_terveriftoday("Aktifitas","Apel"));
        tanggal2.add(new content_terveriftoday("Poin","50"));
        tanggal2.add(new content_terveriftoday("Tempat","Halaman Kantor"));
        tanggal2.add(new content_terveriftoday("Keterangan","Apel Pagi"));
        tanggal2.add(new content_terveriftoday("Status","Terverifikasi"));

        // Adding child data List three
        List<content_terveriftoday> tanggal1 = new ArrayList<content_terveriftoday>();
        tanggal1.add(new content_terveriftoday("Jam Mulai","07:00"));
        tanggal1.add(new content_terveriftoday("Jam Selesai","08:00"));
        tanggal1.add(new content_terveriftoday("Aktifitas","Apel"));
        tanggal1.add(new content_terveriftoday("Poin","50"));
        tanggal1.add(new content_terveriftoday("Tempat","Halaman Kantor"));
        tanggal1.add(new content_terveriftoday("Keterangan","Apel Pagi"));
        tanggal1.add(new content_terveriftoday("Status","Terverifikasi"));

        listDataChild.put(listDataParent.get(0), tanggal3); // Header, Child data
        listDataChild.put(listDataParent.get(1), tanggal2); // Header, Child data
        listDataChild.put(listDataParent.get(2), tanggal1); // Header, Child data

        ExpandableListAdapterTerverifToday listAdapter = new ExpandableListAdapterTerverifToday(context, listDataParent, listDataChild);
        list_view.setAdapter(listAdapter);
    }

    public void back(View view) {
        onBackPressed();
        finish();
    }

    public void today(View view) {
        KAlertDialog alert = new KAlertDialog(this, KAlertDialog.CUSTOM_IMAGE_TYPE);
        alert.setTitleTextSize(20);
        alert.setTitleText("Hari ini");
        alert.setContentText("List LKH yang telah terverifikasi");
        alert.setCustomImage(R.drawable.ic_today,LayoutTerverifToday.this);
        alert.setContentTextSize(16);
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }
}