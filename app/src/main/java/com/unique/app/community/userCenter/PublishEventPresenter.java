package com.unique.app.community.userCenter;

import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.User;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 11/8/16.
 */
public class PublishEventPresenter extends BaseListPresenter<PublishEventActivity,Event> {

    private PublishEventAdapter adapter;

    public PublishEventPresenter(AppCompatActivity activity, RefreshListener listener) {
        super(activity, listener);
    }

    @Override
    protected Observable<List<Event>> requestData(int page) {
        return HttpApi.getPublishEvents(page, (User) User.getCurrentUser())
                .map(new Func1<Response<List<Event>>, List<Event>>() {
                    @Override
                    public List<Event> call(Response<List<Event>> listResponse) {
                        if (listResponse.getCode() == Response.SUCCESS){
                            Timber.d("get event success");
                            return listResponse.getData();
                        }else {
                            Timber.d("getting events failed.message is %s",listResponse.getMessage());
                            return new ArrayList<>();
                        }
                    }
                });
    }

    @Override
    protected PublishEventAdapter getAdapter() {
        return adapter;
    }
    @Override
    public void attachView(PublishEventActivity view) {
        super.attachView(view);
        init();
    }

    private void init(){
        refreshTop();
    }

    @Override
    public void refreshBottom() {
        super.refreshBottom();
        adapter.showFooter();
    }

    @Override
    protected void changeRefreshState(boolean refreshing) {
        super.changeRefreshState(refreshing);
        mView.loading(refreshing);
    }

    public void setAdapter(PublishEventAdapter adapter){
        this.adapter = adapter;
    }


}
