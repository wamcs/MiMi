package com.unique.app.community.maindisplay.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.entity.Event;
import com.unique.app.community.maindisplay.ui.adapter.DisplayEventsAdapter;
import com.unique.app.community.maindisplay.ui.fragment.DisplayEventsFragment;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class DisplayEventsPresenter extends BaseListPresenter<DisplayEventsFragment,Event>{

    private DisplayEventsAdapter adapter;

    public DisplayEventsPresenter(DisplayEventsFragment fragment, RefreshListener listener) {
        super(fragment, listener);
    }

    @Override
    protected Observable<List<Event>> requestData(int page) {
        return HttpApi.getEvents(page).map(new Func1<Response<List<Event>>, List<Event>>() {
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
    public void attachView(DisplayEventsFragment view) {
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

    @Override
    protected DisplayEventsAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DisplayEventsAdapter adapter){
        this.adapter = adapter;
    }
}
