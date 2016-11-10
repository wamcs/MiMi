package com.unique.app.community.details.Widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.details.DetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/10.
 */

public class ReplyDialog extends Dialog {

    @BindView(R.id.detail_edit_text_reply)
    EditText replyEditText;

    private Context context;

    public ReplyDialog(Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.dialog_detail_reply);
        View view = getWindow().getDecorView();
        ButterKnife.bind(this, view);
    }

    public void reply(int who){
        replyEditText.setText("");
        replyEditText.setHint("回复" + ((DetailActivity)context).getMPresent().getCommentPresenter().getAdapter().getData().get(who).getSender().getNickname());
        InputMethodManager inputManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        replyEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND
                        || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    ((DetailActivity)context).getMPresent().getCommentPresenter().replyToWho(who);
                    inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    return true;
                }
                return false;
            }
        });
        replyEditText.requestFocus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inputManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        }, 100);
    }
}
