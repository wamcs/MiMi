package com.unique.app.community.userCenter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 11/8/16.
 */
public class PublishEventAdapter extends BaseAdapter<Event> {


    private FooterViewHolder footerHolder;

    private static final int VIEW_TYPE_UNFINISH = 1;
    private static final int VIEW_TYPE_FINISH = 2;
    private static final int VIEW_TYPE_FOOTER = 3;

    private AppCompatActivity activity;

    PublishEventAdapter(AppCompatActivity appCompatActivity){
        activity = appCompatActivity;
    }

    @Override
    public BaseViewHolder<Event> onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder<Event> result;
        switch (viewType){
            case VIEW_TYPE_FINISH:
                result = new PublishEventFinishViewHolder(parent,activity);
                break;
            case VIEW_TYPE_UNFINISH:
                result = new PublishEventUnFinishViewHolder(parent,activity);
                break;
            case VIEW_TYPE_FOOTER:
                footerHolder = new FooterViewHolder(parent);
                result = footerHolder;
                break;
            default:
                result = new PublishEventFinishViewHolder(parent,activity);
                break;
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
            case VIEW_TYPE_FOOTER:
                break;
            default:
                holder.bindData(dataList.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER;
        }
        if (dataList.get(position).getState() == Event.end){
            return VIEW_TYPE_FINISH;
        }
        return VIEW_TYPE_UNFINISH;
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
