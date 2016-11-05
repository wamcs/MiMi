package com.unique.app.community.details.CommentFragment;

import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.utils.TimeUtils;
import com.unique.app.community.utils.ToastUtil;
import com.unique.app.community.widget.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class CommentVewHolder extends BaseViewHolder<EventComment> {

    @BindView(R.id.detail_fra_icon_comment)
    CircularImageView mIconComment;
    @BindView(R.id.detail_fra_text_view_name_comment)
    TextView mNameText;
    @BindView(R.id.detail_fra_text_view_join_comment)
    TextView mStatusText;
    @BindView(R.id.detail_fra_text_view_time_comment)
    TextView mTimeText;
    @BindView(R.id.detail_fra_text_view_text_comment)
    TextView mContentText;

    private OnReplyListener replyListener;
    private DetailCommentFragment fragment;


    public CommentVewHolder(ViewGroup parent, DetailCommentFragment fragment) {
        super(parent, R.layout.item_rv_detail_comment);
        ButterKnife.bind(this, itemView);
        this.fragment = fragment;
    }

    @Override
    public void bindData(EventComment data) {
        Glide.with(fragment).load(data.getSender().getAvatat().getUrl())
                .error(R.mipmap.default_avatar).into(mIconComment);
        mNameText.setText(data.getSender().getNickname());
        mStatusText.setVisibility(View.VISIBLE);
        mTimeText.setText(TimeUtils.parseDate(data.getCreatedAt()));
        mContentText.setText(data.getContent());
    }

    @OnClick(R.id.detail_fra_text_view_reply_comment)
    void reply(){
        replyListener.onReply(getAdapterPosition());
    }

    @OnClick(R.id.detail_fra_icon_comment)
    void showUser(){
        //TODO:do some things
    }

    public interface OnReplyListener {
        void onReply(int who);
    }

    public void setOnReplyListener(OnReplyListener replyListener) {
        this.replyListener = replyListener;
    }

}
