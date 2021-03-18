package jepara.app.kinar.Fragment.Ekinerja;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
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

import jepara.app.kinar.Adapter.ExpandableListAdapterVerif;
import jepara.app.kinar.R;
import jepara.app.kinar.model.content_verif;

public class FragmentVerifLkh extends Fragment {

    private Context context;
    private ExpandableListView list_view;
    List<String> listDataParent;
    HashMap<String, List<content_verif>> listDataChild;
    private SwipeRefreshLayout SwipeRefresh;
    RelativeLayout toolbarLayout;
    EditText search;
    ImageButton filter;
    FrameLayout emptylayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = this.getActivity();
        View view = inflater.inflate(R.layout.fragment_verif_lkh, container, false);

        search = view.findViewById(R.id.search_verif);

        filter = view.findViewById(R.id.filter_verif);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Filter Verifikasi LKH", Toast.LENGTH_SHORT).show();
            }
        });

        // Inisialisasi SwipeRefreshLayout
        SwipeRefresh = view.findViewById(R.id.refreshVerif);

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

        emptylayout = view.findViewById(R.id.es_layout_verif);

        list_view = view.findViewById(R.id.ListView_verif);
        createListData();
        registerForContextMenu(list_view);
        toolbarLayout = view.findViewById(R.id.toolbar_layoutVerif);
        search = view.findViewById(R.id.search_verif);
        filter = view.findViewById(R.id.filter_verif);

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
                if(firstVisibleItem==0){
                    SwipeRefresh.setEnabled(true);
                }
                else {
                    SwipeRefresh.setEnabled(false);
                }
            }
        });
    }

    private void createListData() {
        listDataParent = new ArrayList<String>();
        listDataChild = new HashMap<String, List<content_verif>>();

        // Adding child data
        listDataParent.add("Jacob Jones");
        listDataParent.add("Floys Miles");

        if(listDataParent.size() != 0)
        {
            emptylayout.setVisibility(View.GONE);
        }
        else {
            emptylayout.setVisibility(View.VISIBLE);
        }

        // Adding child data List one
        List<content_verif> nama3 = new ArrayList<content_verif>();
        nama3.add(new content_verif("NIP","230482880234"));
        nama3.add(new content_verif("Jabatan","Sekretariat"));
        nama3.add(new content_verif("Poin","140"));
        nama3.add(new content_verif("Lihat LKH","-"));
        nama3.add(new content_verif("Status","Terverifikasi"));

        // Adding child data List two
        List<content_verif> nama2 = new ArrayList<content_verif>();
        nama2.add(new content_verif("NIP","230482880234"));
        nama2.add(new content_verif("Jabatan","Sekretariat"));
        nama2.add(new content_verif("Poin","140"));
        nama2.add(new content_verif("Lihat LKH","-"));
        nama2.add(new content_verif("Status","Terverifikasi"));


        listDataChild.put(listDataParent.get(0), nama3); // Header, Child data
        listDataChild.put(listDataParent.get(1), nama2); // Header, Child data

        ExpandableListAdapterVerif listAdapterVerif = new ExpandableListAdapterVerif(context, listDataParent, listDataChild);
        list_view.setAdapter(listAdapterVerif);
    }
}