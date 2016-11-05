package com.unique.app.community.details.CommentFragment;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.details.DetailActivity;
import com.unique.app.community.details.Widget.KeyboardListenerLayout;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.entity.User;
import com.unique.app.community.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/26.
 */

public class DetailCommentPresenter extends BaseListPresenter<DetailCommentFragment, EventComment> {

    private DetailCommentAdapter commentAdapter;

    public DetailCommentPresenter(Fragment fragment, RefreshListener listener) {
        super(fragment, listener);
        commentAdapter = new DetailCommentAdapter();
        commentAdapter.setFragment((DetailCommentFragment)fragment);
    }

    @Override
    protected Observable<List<EventComment>> requestData(int page) {
        // FIXME: 16/11/4
        // Test data
        ArrayList<EventComment> testList = new ArrayList<>();
        User testUser = new User();
        testUser.setNickname("二狗子");
        EventComment testComment = new EventComment();
        testComment.setSender(testUser);
        testComment.setContent("好棒呀，摄影大大带我飞");
        testList.add(testComment);
        testList.add(testComment);
        testList.add(testComment);
        return Observable.just(testList);
    }

    @Override
    public DetailCommentAdapter getAdapter() {
        return commentAdapter;
    }

    public void comment(String comment){
        // FIXME: 16/11/4
        ToastUtil.TextToast("Comment " + comment);
    }

}
