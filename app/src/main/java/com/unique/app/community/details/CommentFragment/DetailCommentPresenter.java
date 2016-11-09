package com.unique.app.community.details.CommentFragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.unique.app.community.base.recyclerView.BaseListPresenter;
import com.unique.app.community.base.recyclerView.RefreshListener;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.EventComment;
import com.unique.app.community.entity.User;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.OtherApi;
import com.unique.app.community.net.Response;
import com.unique.app.community.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/10/26.
 */

public class DetailCommentPresenter extends BaseListPresenter<DetailCommentFragment, EventComment> {

    private CommentAdapter commentAdapter;
    private Event event;
    private List<EventComment> eventComments;

    public DetailCommentPresenter(Fragment fragment, RefreshListener listener, Event event) {
        super(fragment, listener);
        commentAdapter = new CommentAdapter((DetailCommentFragment)fragment);
        this.event =event;
        getEventComment();
    }

    private void getEventComment(){
        HttpApi.getRelativeEventComment(event.getComments())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(listResponse -> {
                    if (listResponse.getCode() == Response.SUCCESS) {
                        eventComments = listResponse.getData();
                    }
                }, throwable -> {
                    Timber.d("get event comment error:%s"+throwable.toString());
                });
    }

    @Override
    protected Observable<List<EventComment>> requestData(int page) {
        int start = (page-1)*ITEM_NUMBER;
        int end = page*ITEM_NUMBER-1;
        return Observable.just(eventComments).flatMap(new Func1<List<EventComment>, Observable<EventComment>>() {
            @Override
            public Observable<EventComment> call(List<EventComment> eventComments) {
                return Observable.from(eventComments);
            }
        }).filter(new Func1<EventComment, Boolean>() {
            @Override
            public Boolean call(EventComment eventComment) {
                return start<=eventComments.indexOf(eventComment)&&eventComments.indexOf(eventComment)<=end;
            }
        }).toList();
    }

    @Override
    public CommentAdapter getAdapter() {
        return commentAdapter;
    }

    public void comment(String comment){
        // FIXME: 16/11/4
        User receiver = event.getSponsor();
        User sender = (User) User.getCurrentUser();
        EventComment eventComment = new EventComment();
        eventComment.setContent(comment);
        eventComment.setEvent(event);
        eventComment.setReceiver(receiver);
        eventComment.setSender(sender);
        HttpApi.postEventComment(eventComment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(voidResponse -> {
                    if (voidResponse.getCode() == Response.SUCCESS) {
                        eventComments.add(0,eventComment);
                        refreshTop();
                    }
                }, throwable -> {
                    Timber.d("post event comment error: %s",throwable.toString());
                });
        ToastUtil.TextToast("Comment " + comment);
    }

    public boolean isUserInParticipant(User user){
        //TODO:还没想好怎么写
        return false;
    }
}
