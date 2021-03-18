package jepara.app.kinar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import jepara.app.kinar.R;
import jepara.app.kinar.model.content_verif;

public class ExpandableListAdapterVerif extends BaseExpandableListAdapter {

    private Context contextv;
    private List<String> listDataHeaderv;
    private HashMap<String, List<content_verif>> listDataChildv;

    // constructor
    public ExpandableListAdapterVerif(Context contextv, List<String> listDataHeaderv, HashMap<String, List<content_verif>> listDataChildv) {
        this.contextv = contextv;
        this.listDataHeaderv = listDataHeaderv;
        this.listDataChildv = listDataChildv;
    }


    @Override
    public int getGroupCount() {
        return this.listDataHeaderv.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChildv.get(this.listDataHeaderv.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeaderv.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChildv.get(this.listDataHeaderv.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.contextv.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_verif, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.text_title);

//        lblListHeader.setTypeface(null, Typeface.BOLD);

        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.contextv.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_green, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.text_desc);
        TextView contentListChild = (TextView) convertView.findViewById(R.id.text_content);

        content_verif contentVerif = (content_verif) getChild(groupPosition, childPosition);
        txtListChild.setText(contentVerif.getTitle());
        contentListChild.setText(contentVerif.getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
