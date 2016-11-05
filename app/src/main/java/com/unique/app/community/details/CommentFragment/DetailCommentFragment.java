package com.unique.app.community.details.CommentFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseListFragment;
import com.unique.app.community.base.Mvp.IView;
import com.unique.app.community.details.DetailActivity;
import com.unique.app.community.details.Widget.KeyboardListenerLayout;

import butterknife.BindView;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/26.
 */

public class DetailCommentFragment extends BaseListFragment<DetailCommentPresenter>
        implements IView {

    @BindView(R.id.detail_ask_comment_edit_text)
    EditText commentEditText;
    @BindView(R.id.detail_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.detail_text_view_read_more)
    TextView readMore;

    @Override
    public void onRefreshStateChanged(boolean isRefreshing) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initEventAndData() {
        setEnterToSend();
        mPresenter.refreshTop();
        commentEditText.setHint(getResources().getString(R.string.wanna_comment_sth));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically(){
                return false;
            }

            @Override
            public boolean canScrollHorizontally(){
                return false;
            }
        });
        recyclerView.setAdapter(mPresenter.getAdapter());

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.refreshBottom();
            }
        });
    }

    @Override
    public DetailCommentPresenter getPresenter() {
        return ((DetailActivity)getActivity()).getMPresent().getCommentPresenter();
    }

    public void comment(){
        mPresenter.comment(commentEditText.getText().toString());
        commentEditText.setText("");
    }

    private void setEnterToSend(){
        commentEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND
                        || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    comment();
                    return true;
                }
                return false;
            }
        });
    }
}
