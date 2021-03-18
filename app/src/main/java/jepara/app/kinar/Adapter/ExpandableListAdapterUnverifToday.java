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
import jepara.app.kinar.model.content_unveriftoday;

public class ExpandableListAdapterUnverifToday extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<content_unveriftoday>> _listDataChild;

    public ExpandableListAdapterUnverifToday(Context _context, List<String> _listDataHeader, HashMap<String, List<content_unveriftoday>> _listDataChild) {
        this._context = _context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
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
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_orange, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.text_title);

        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_orange, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.text_desc);
        TextView contentListChild = (TextView) convertView.findViewById(R.id.text_content);

        content_unveriftoday investment = (content_unveriftoday) getChild(groupPosition, childPosition);

        txtListChild.setText(investment.getTitle());
        contentListChild.setText(investment.getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
