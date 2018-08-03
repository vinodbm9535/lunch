package com.example.admin.lunch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.lunch.R;

import java.util.ArrayList;

public class EntreeCalenderMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> data;
    Context context;

    public EntreeCalenderMenuAdapter(Context context, ArrayList<String> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recyclerview_entries, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView imgEntree;
        private final TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvEntreeName);
            imgEntree = itemView.findViewById(R.id.imgEntree);
            desc = itemView.findViewById(R.id.desc);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
