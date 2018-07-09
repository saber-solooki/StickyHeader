package com.saber.stickyheader.stickyView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.saber.stickyheader.stickyData.HeaderData;
import com.saber.stickyheader.stickyData.StickyMainData;

import java.util.ArrayList;
import java.util.List;

public abstract class StickHeaderRecyclerView<D extends StickyMainData, H extends HeaderData> extends RecyclerView.Adapter implements StickHeaderItemDecoration.StickyHeaderInterface{
    private List<StickyMainData> mData;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        StickHeaderItemDecoration stickHeaderDecoration = new StickHeaderItemDecoration(this);
        recyclerView.addItemDecoration(stickHeaderDecoration);
    }

    @Override
    public final int getItemViewType(int position) {
        if (mData.get(position) instanceof HeaderData) {
            return ((HeaderData) mData.get(position)).getHeaderType();
        }
        return getViewType(position);
    }

    @Override
    public boolean isHeader(int itemPosition) {
        return mData.get(itemPosition) instanceof HeaderData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        return ((HeaderData) mData.get(headerPosition)).getHeaderLayout();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= 1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    public void setHeaderAndData(@NonNull List<D> datas, @Nullable HeaderData header) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        if (header != null) {
            mData.add(header);
        }

        mData.addAll(datas);
    }

    protected int getViewType(int pos){
        return 0;
    }

    protected D getDataInPosition(int position) {
        return (D) mData.get(position);
    }

    protected H getHeaderDataInPosition(int position) {
        return (H) mData.get(position);
    }
}
