package com.example.nan.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by nan on 2016/4/9.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private List<Item> DataList;
    private SparseArray<Item> mKeyedDatalist;

    public MyAdapter(Context context, List dataList) {
        this.context = context;
        DataList = dataList;
        mKeyedDatalist=new SparseArray<>();
        reorderSections();
    }

    private void reorderSections() {
        mKeyedDatalist.clear();
        int startPosition = 0;
        for (Item item : DataList) {
            mKeyedDatalist.put(startPosition, item);
            startPosition += item.getCount();
        }
    }


    @Override
    public int getItemViewType(int position) {
        Log.i("wu",position+""+"   "+getItemCount());
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean isHeaderView(int position) {
        for (int i = 0; i < mKeyedDatalist.size(); ++i) {
            if (mKeyedDatalist.keyAt(i) == position) {
                return true;
            }
        }
        return false;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("wu",""+"   "+getItemCount());
        if (viewType == TYPE_HEADER) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_header, parent, false));
        }
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false));

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (Item item : DataList
                ) {
            count += item.getCount();
        }
        return count;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
