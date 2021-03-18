package jepara.app.kinar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jepara.app.kinar.R;
import jepara.app.kinar.model.model_notif;

public class RecyclerviewAdapterNotif extends RecyclerView.Adapter<RecyclerviewAdapterNotif.RecyclerviewAdapterNotifViewHolder> {

    private ArrayList<model_notif> dataList;

    public RecyclerviewAdapterNotif(ArrayList<model_notif> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public jepara.app.kinar.Adapter.RecyclerviewAdapterNotif.RecyclerviewAdapterNotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notification_layout, parent, false);
        return new RecyclerviewAdapterNotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterNotifViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtDesc.setText(dataList.get(position).getDesc());
        holder.txtDate.setText(dataList.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class RecyclerviewAdapterNotifViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtDesc, txtDate;
        public RecyclerviewAdapterNotifViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.notif_title);
            txtDesc = (TextView) itemView.findViewById(R.id.notif_body);
            txtDate = (TextView) itemView.findViewById(R.id.notif_date);
        }
    }
}
