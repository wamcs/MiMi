package com.unique.app.community.details.AskFragment;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.EventComment;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class DetailAskAdapter extends BaseAdapter<EventComment> {

    @Override
    public BaseViewHolder<EventComment> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailAskViewHolder(parent, R.layout.item_rv_detail_ask);
    }
}
