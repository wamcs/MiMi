package com.unique.app.community.base.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> dataList;


    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void addData(List<T> data){
        if (null == dataList){
            setData(data);
        }
        //position of last element which in dataList
        int p = dataList.size();
        dataList.addAll(data);
        notifyItemInserted(p);
    }

    public void setData(List<T> data){
        dataList = data;
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return dataList;
    }
}
