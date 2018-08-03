package com.example.admin.lunch.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.lunch.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateCalenderMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> data;
    Context context;
    private static UpdateDataClickListener sClickListener;
    private int mLastSelectedItem = -1;

    public DateCalenderMenuAdapter(Context context, ArrayList<String> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recyclerview_dates, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            mBackground = (LinearLayout) itemView.findViewById(R.id.mBackground);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            sClickListener.onItemClick(getAdapterPosition());
        }
    }

    public void setOnItemClickListener(UpdateDataClickListener clickListener) {
        sClickListener = clickListener;
    }

    public interface UpdateDataClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
