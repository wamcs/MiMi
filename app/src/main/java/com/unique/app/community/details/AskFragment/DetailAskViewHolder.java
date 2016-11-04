package com.unique.app.community.details.AskFragment;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.utils.ToastUtil;

import butterknife.BindView;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/3.
 */

public class DetailAskViewHolder extends BaseViewHolder<EventComment> {

    private ImageView iconImage;
    private TextView nameTextView;
    private TextView timeTextView;
    private TextView queTextView;
    private TextView ansTextView;

    public DetailAskViewHolder(View itemView) {
        super(itemView);
    }

    public DetailAskViewHolder(ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bindData(EventComment data) {
        iconImage = (ImageView)itemView.findViewById(R.id.detail_fra_icon_ask);
        nameTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_name_ask);
        timeTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_time_ask);
        queTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_question_ask);
        ansTextView = (TextView)itemView.findViewById(R.id.detail_fra_text_view_answer_ask);

        // FIXME: 16/11/4 Get the icon of sender
        iconImage.setImageBitmap(BitmapFactory.decodeResource(itemView.getResources(), R.mipmap.ic_launcher));
        nameTextView.setText(data.getSender().getNickname());
        // FIXME: 16/11/4 Get the time of commit
        timeTextView.setText("今天 19：00");
        // FIXME: 16/11/4 Get the question
        queTextView.setText("楼主大几呀？是专业摄影师吗？");
        // FIXME: 16/11/4 Get the answer
        ansTextView.setText("大三，算不上专业，大学期间一直都在玩摄影，技术也学了不少");

        initialListeners();
    }

    // FIXME: 16/11/4
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
    }
}
