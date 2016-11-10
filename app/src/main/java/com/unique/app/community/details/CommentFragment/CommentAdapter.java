package com.unique.app.community.details.CommentFragment;

import android.view.ViewGroup;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.EventComment;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class CommentAdapter extends BaseAdapter<EventComment> {

    private CommentVewHolder detailCommentVewHolder;
    private DetailCommentFragment fragment;

    public CommentAdapter(DetailCommentFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public BaseViewHolder<EventComment> onCreateViewHolder(ViewGroup parent, int viewType) {
        detailCommentVewHolder = new CommentVewHolder(parent, fragment);
        return  detailCommentVewHolder;
    }

}
