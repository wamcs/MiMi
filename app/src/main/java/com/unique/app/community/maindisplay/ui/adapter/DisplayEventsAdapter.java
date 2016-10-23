package com.unique.app.community.maindisplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.Event;
import com.unique.app.community.maindisplay.ui.fragment.DisplayEventsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/21/16.
 */
public class DisplayEventsAdapter extends BaseAdapter<Event> {

    private FooterViewHolder footerHolder;

    private DisplayEventsFragment fragment;

    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_FOOTER = 2;


    public DisplayEventsAdapter(DisplayEventsFragment fragment){
        this.fragment = fragment;
    }


    @Override
    public BaseViewHolder<Event> onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder<Event> result;
        switch (viewType){
            case VIEW_TYPE_NORMAL:
                result = new DisplayEventViewHolder(parent,fragment);
                break;
            case VIEW_TYPE_FOOTER:
                footerHolder = new FooterViewHolder(parent);
                result = footerHolder;
                break;
            default:
                result = new DisplayEventViewHolder(parent,fragment);
        }
        return result;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<Event> holder, int position) {
        if (dataList == null){
            return;
        }
        int viewType = getItemViewType(position);
        switch (viewType){
            case VIEW_TYPE_NORMAL:
                holder.bindData(dataList.get(position));
                break;
            case VIEW_TYPE_FOOTER:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER;
        }
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size()+1;
    }

    public void showFooter() {
        footerHolder.mRoot.setVisibility(View.VISIBLE);
    }

    public boolean isFooterVisible() {
        return footerHolder != null && footerHolder.mRoot.getVisibility() == View.VISIBLE;
    }

    public void hideFooter() {
        footerHolder.mRoot.setVisibility(View.GONE);
    }



    class FooterViewHolder extends BaseViewHolder<Event> {
        @BindView(R.id.item_loading_root)
        RelativeLayout mRoot;

        public FooterViewHolder(ViewGroup parent) {
            super(fromResLayout(parent, R.layout.item_loading_bottom));
            ButterKnife.bind(this, itemView);
            mRoot.setVisibility(View.GONE);
        }


        @Override
        public void bindData(Event data) {

        }
    }


}
