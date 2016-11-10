package com.unique.app.community.details.CommentFragment;

import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unique.app.community.R;
import com.unique.app.community.base.Mvp.BaseFragment;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.details.DetailActivity;
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

    private DetailCommentFragment fragment;


    public CommentVewHolder(ViewGroup parent, DetailCommentFragment fragment) {
        super(parent, R.layout.item_rv_detail_comment);
        ButterKnife.bind(this, itemView);
        this.fragment = fragment;
    }

    @Override
    public void bindData(EventComment data) {
        //Glide.with(fragment).load(data.getSender().getAvatat().getUrl())
        //        .error(R.mipmap.default_avatar).into(mIconComment);
        mNameText.setText(data.getSender().getNickname());
        mStatusText.setVisibility(View.VISIBLE);
        //mTimeText.setText(TimeUtils.parseDate(data.getCreatedAt()));
        mContentText.setText(makeText(data));
    }

    @OnClick(R.id.detail_fra_text_view_reply_comment)
    void reply(){
        ((DetailActivity)fragment.getActivity()).reply(getAdapterPosition());
    }

    @OnClick(R.id.detail_fra_icon_comment)
    void showUser(){
        //TODO:do some things
    }

    private SpannableString makeText(EventComment data){
        if(data.getRecevier() != null) {
            String name = data.getRecevier().getNickname();
            SpannableString ss = new SpannableString("回复@" + name + "：" + data.getContent());
            ss.setSpan(new ForegroundColorSpan(fragment.getResources().getColor(R.color.basic_orange)), 2, 3 + name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }else{
            return new SpannableString(data.getContent());
        }
    }
}
