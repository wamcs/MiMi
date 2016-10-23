package com.unique.app.community.maindisplay.presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.unique.app.community.base.recyclerView.BaseAdapter;
import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.entity.Event;
import com.unique.app.community.maindisplay.ui.fragment.DisplayEventsFragment;

import java.util.List;

import rx.Observable;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/8/16.
 */
public class DisplayEventsPresenter extends BaseListPresenter<DisplayEventsFragment,Event>{


    public DisplayEventsPresenter(Fragment fragment, RefreshListener listener) {
        super(fragment, listener);
    }

    @Override
    protected Observable<List<Event>> requestData(int page) {
        return null;
    }

    @Override
    protected BaseAdapter<Event> getAdapter() {
        return null;
    }
}
