package com.unique.app.community.userCenter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.EventTag;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;
import com.unique.app.community.utils.Inflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 *
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 11/8/16.
 */
public class PublishEventUnFinishViewHolder extends BaseViewHolder<Event> {

    @BindView(R.id.display_item_image)
    ImageView displayItemImage;
    @BindView(R.id.display_item_title_text)
    TextView displayItemTitleText;
    @BindView(R.id.display_item_tags_container)
    LinearLayout displayItemTagsContainer;

    private AppCompatActivity activity;

    public PublishEventUnFinishViewHolder(ViewGroup viewGroup, AppCompatActivity activity) {
        super(viewGroup, R.layout.item_my_event_unfinish);
        this.activity = activity;
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void bindData(Event data) {
        if (data.getImage() == null){
            return;
        }
        Glide.with(activity).load(data.getImage())
                .error(R.mipmap.need_to_remove)
                .into(displayItemImage);
        displayItemTitleText.setText(data.getSubject());
        parseTags(data);
    }

    private void parseTags(Event event) {
        HttpApi.getRelativeEventTag(event.getTags())
                .map((Func1<Response<List<EventTag>>, List<EventTag>>) listResponse -> {
                    if (listResponse.getCode() == Response.SUCCESS){
                        return listResponse.getData();
                    }else {
                        Timber.d("getting relative tags failed.message is %s",listResponse.getMessage());
                        return new ArrayList<>();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(eventTags -> {
                    ArrayList<String> tags = new ArrayList<>();

                    for (EventTag tag :eventTags) {
                        tags.add(tag.getTitle());
                    }

                    for (String tag : tags){
                        View view = Inflater.inflate(R.layout.item_event_tag,displayItemTagsContainer,false);
                        TextView text = (TextView) view.findViewById(R.id.display_item_event_tag);
                        text.setText(tag);
                        displayItemTagsContainer.addView(view);
                    }
                });

    }

    @OnClick(R.id.publish_activity_unfinish_item_modify_button)
    void modify(){
        //TODO:modify event
    }

    @OnClick(R.id.publish_activity_unfinish_item_user_list_button)
    void inspect(){
        //TODO:inspect user list
    }
}
