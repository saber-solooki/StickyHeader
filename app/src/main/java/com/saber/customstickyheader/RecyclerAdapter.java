package com.saber.customstickyheader;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;

public class RecyclerAdapter extends StickHeaderRecyclerView<CustomerData, HeaderDataImpl> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HeaderDataImpl.HEADER_TYPE_1:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header1_item_recycler, parent, false));
            case HeaderDataImpl.HEADER_TYPE_2:
                return new Header2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header2_item_recycler, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(position);
        } else if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).bindData(position);
        } else if (holder instanceof Header2ViewHolder){
            ((Header2ViewHolder) holder).bindData(position);
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {

        /*TextView tv = header.findViewById(R.id.tvHeader);
        tv.setText(String.valueOf(headerPosition / 5));*/
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }

        void bindData(int position) {
            tvHeader.setText(String.valueOf(position / 5));
        }
    }

    class Header2ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        Header2ViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }

        void bindData(int position) {
            tvHeader.setText(String.valueOf(position / 5));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRows;

        ViewHolder(View itemView) {
            super(itemView);
            tvRows = itemView.findViewById(R.id.tvRows);
        }

        void bindData(int position) {
            getDataInPosition(position).getFelan();
            tvRows.setText("saber" + position);
            ((ViewGroup) tvRows.getParent()).setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }
}
