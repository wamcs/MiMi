package com.unique.app.community.details.CommentFragment;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.details.DetailActivity;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class DetailCommentAdapter extends BaseAdapter<EventComment> {

    private DetailCommentVewHolder detailCommentVewHolder;
    private DetailCommentFragment fragment;

    @Override
    public BaseViewHolder<EventComment> onCreateViewHolder(ViewGroup parent, int viewType) {
        detailCommentVewHolder = new DetailCommentVewHolder(parent, R.layout.item_rv_detail_comment);
        detailCommentVewHolder.setOnReplyListener(new DetailCommentVewHolder.OnReplyListener() {
            @Override
            public void onReply(int who) {
                ((DetailActivity)fragment.getActivity()).reply(who);
            }
        });
        return  detailCommentVewHolder;
    }

    public void setFragment(DetailCommentFragment fragment){
        this.fragment = fragment;
    }
}
