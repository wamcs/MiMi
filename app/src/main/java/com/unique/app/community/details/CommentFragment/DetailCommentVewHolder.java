package com.unique.app.community.details.CommentFragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.details.DetailActivity;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.utils.ToastUtil;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class DetailCommentVewHolder extends BaseViewHolder<EventComment> {

    private ImageView iconImage;
    private TextView nameTextView;
    private TextView joinTextView;
    private TextView timeTextView;
    private TextView textTextView;
    private TextView replyTextView;

    private OnReplyListener replyListener;

    public DetailCommentVewHolder(View itemView) {
        super(itemView);
    }

    public DetailCommentVewHolder(ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bindData(EventComment data) {
        iconImage = (ImageView)itemView.findViewById(R.id.detail_fra_icon_comment);
        nameTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_name_comment);
        joinTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_join_comment);
        timeTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_time_comment);
        textTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_text_comment);
        replyTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_reply_comment);

        // FIXME: 16/11/4 Get the icon of sender
        iconImage.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), R.mipmap.ic_launcher));
        nameTextView.setText(data.getSender().getNickname());
        // FIXME: 16/11/4 Get whether has joined
        joinTextView.setVisibility(View.VISIBLE);
        // FIXME: 16/11/4 Get the time of commit
        timeTextView.setText("今天 19：00");
        textTextView.setText(data.getContent());

        initialListeners();
    }

    private void initialListeners(){
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.TextToast("Click Icon");
            }
        });

        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.TextToast("Click Name");
            }
        });

        replyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replyListener.onReply(getAdapterPosition());
            }
        });
    }

    public interface OnReplyListener{
        void onReply(int who);
    }

    public void setOnReplyListener(OnReplyListener replyListener){
        this.replyListener = replyListener;
    }

}
