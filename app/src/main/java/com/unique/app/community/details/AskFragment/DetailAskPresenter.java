package com.unique.app.community.details.AskFragment;

import android.support.v4.app.Fragment;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
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

public class DetailAskPresenter extends BaseListPresenter<DetailAskFragment, EventComment> {

    private DetailAskAdapter askAdapter;

    public DetailAskPresenter(Fragment fragment, RefreshListener listener) {
        super(fragment, listener);
        askAdapter = new DetailAskAdapter();
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
        testList.add(testComment);
        testList.add(testComment);
        return Observable.just(testList);
    }

    @Override
    protected DetailAskAdapter getAdapter() {
        return askAdapter;
    }

    public void ask(String question){
        // FIXME: 16/11/4
        ToastUtil.TextToast("Ask " + question);
    }

}
