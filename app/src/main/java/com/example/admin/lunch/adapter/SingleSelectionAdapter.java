package com.example.admin.lunch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.lunch.ItemModel;
import com.example.admin.lunch.NameValue;
import com.example.admin.lunch.R;

import java.util.List;

public class SingleSelectionAdapter extends RecyclerView.Adapter {

    private List<NameValue> itemModels;
    private Context context;
    private int lastCheckedPosition = -1;

    public SingleSelectionAdapter(Context context, List itemModels) {
        this.itemModels = itemModels;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recyclerview_dates, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NameValue model = itemModels.get(position);
        initializeViews(model, holder, position);
    }


    private void initializeViews(final NameValue model, final RecyclerView.ViewHolder holder, int position) {
        if (model.dayNum == lastCheckedPosition) {
            ((ItemViewHolder) holder).backGround.setSelected(true);
        } else {
            ((ItemViewHolder) holder).backGround.setSelected(false);
        }
        ((ItemViewHolder) holder).tvDayString.setText(model.dayString.substring(0, 3));
        ((ItemViewHolder) holder).tvDayNum.setText(String.valueOf(model.dayNum));
        ((ItemViewHolder) holder).backGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = model.dayNum;
                notifyItemRangeChanged(0, itemModels.size());

            }
        });
    }

    public NameValue getSelectedItem() {
        NameValue model = itemModels.get(lastCheckedPosition);
        return model;
    }

    public int selectedPosition() {
        return lastCheckedPosition;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayString;
        TextView tvDayNum;
        LinearLayout backGround;

        public ItemViewHolder(View itemView) {
            super(itemView);
            backGround = itemView.findViewById(R.id.mBackground);
            tvDayNum = itemView.findViewById(R.id.tvDayNum);
            tvDayString = itemView.findViewById(R.id.tvDayString);
        }
    }

    public void clearAndSet(List<NameValue> newNameValues) {
        this.itemModels = newNameValues;
        notifyDataSetChanged();
    }
}

