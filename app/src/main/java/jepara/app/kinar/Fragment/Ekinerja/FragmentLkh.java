package jepara.app.kinar.Fragment.Ekinerja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jepara.app.kinar.Adapter.ExpandableListAdapterLkh;
import jepara.app.kinar.R;
import jepara.app.kinar.model.content_lkh;
import jepara.app.kinar.view.EditLkh;


public class FragmentLkh extends Fragment {

    private Context context;
    private ExpandableListView list_view;
    List<String> listDataParent;
    HashMap<String, List<content_lkh>> listDataChild;
    private SwipeRefreshLayout SwipeRefresh;
    RelativeLayout toolbarLayout;
    EditText search;
    ImageButton filter;
    FrameLayout emptylayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.context = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_lkh, container, false);
        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = view.findViewById(R.id.refreshLkh);

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emptylayout = view.findViewById(R.id.es_layout_lkh);

        list_view = (ExpandableListView) view.findViewById(R.id.list_view);
        createListData();
        registerForContextMenu(list_view);
        toolbarLayout = view.findViewById(R.id.toolbar_layout);

        search = view.findViewById(R.id.search_lkh);

        filter = view.findViewById(R.id.filter_lkh);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Filter LKH", Toast.LENGTH_SHORT).show();
            }
        });

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
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
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

        getActivity().getMenuInflater().inflate(R.menu.option_ekinerja,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Intent intent = new Intent(getActivity(), EditLkh.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                Toast.makeText(getActivity(),"LKH Terhapus",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createListData() {

        listDataParent = new ArrayList<String>();
        listDataChild = new HashMap<String, List<content_lkh>>();

        // Adding child data
        listDataParent.add("Kamis, 4 Februari 2021");
        listDataParent.add("Selasa, 2 Februari 2021");
        listDataParent.add("Senin, 1 Februari 2021");

        if(listDataParent.size() != 0)
        {
            emptylayout.setVisibility(View.GONE);
        }
        else {
            emptylayout.setVisibility(View.VISIBLE);
        }

        // Adding child data List one
        List<content_lkh> tanggal3 = new ArrayList<content_lkh>();
        tanggal3.add(new content_lkh("Jam Mulai","07:00"));
        tanggal3.add(new content_lkh("Jam Selesai","08:00"));
        tanggal3.add(new content_lkh("Aktifitas","Apel"));
        tanggal3.add(new content_lkh("Poin","50"));
        tanggal3.add(new content_lkh("Tempat","Halaman Kantor"));
        tanggal3.add(new content_lkh("Keterangan","Apel Pagi"));
        tanggal3.add(new content_lkh("Status","Terverifikasi"));

        // Adding child data List two
        List<content_lkh> tanggal2 = new ArrayList<content_lkh>();
        tanggal2.add(new content_lkh("Jam Mulai","07:00"));
        tanggal2.add(new content_lkh("Jam Selesai","08:00"));
        tanggal2.add(new content_lkh("Aktifitas","Apel"));
        tanggal2.add(new content_lkh("Poin","50"));
        tanggal2.add(new content_lkh("Tempat","Halaman Kantor"));
        tanggal2.add(new content_lkh("Keterangan","Apel Pagi"));
        tanggal2.add(new content_lkh("Status","Terverifikasi"));

        // Adding child data List three
        List<content_lkh> tanggal1 = new ArrayList<content_lkh>();
        tanggal1.add(new content_lkh("Jam Mulai","07:00"));
        tanggal1.add(new content_lkh("Jam Selesai","08:00"));
        tanggal1.add(new content_lkh("Aktifitas","Apel"));
        tanggal1.add(new content_lkh("Poin","50"));
        tanggal1.add(new content_lkh("Tempat","Halaman Kantor"));
        tanggal1.add(new content_lkh("Keterangan","Apel Pagi"));
        tanggal1.add(new content_lkh("Status","Terverifikasi"));

        listDataChild.put(listDataParent.get(0), tanggal3); // Header, Child data
        listDataChild.put(listDataParent.get(1), tanggal2); // Header, Child data
        listDataChild.put(listDataParent.get(2), tanggal1); // Header, Child data

        ExpandableListAdapterLkh listAdapter = new ExpandableListAdapterLkh(context, listDataParent, listDataChild);
        list_view.setAdapter(listAdapter);
    }
}