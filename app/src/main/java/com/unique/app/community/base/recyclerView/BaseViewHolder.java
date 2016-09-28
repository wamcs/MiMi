package com.unique.app.community.base.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 9/28/16.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, int layoutId) {
        super(fromResLayout(parent, layoutId));
    }


    public static View fromResLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public static View fromResLayout(Context context, int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public abstract void bindData(T data);

}
