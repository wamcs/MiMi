package com.unique.app.community.maindisplay.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.bumptech.glide.Glide;
import com.unique.app.community.R;
import com.unique.app.community.base.recyclerView.BaseViewHolder;
import com.unique.app.community.entity.Event;
import com.unique.app.community.entity.EventTag;
import com.unique.app.community.maindisplay.ui.fragment.DisplayEventsFragment;
import com.unique.app.community.net.HttpApi;
import com.unique.app.community.net.Response;
import com.unique.app.community.utils.Inflater;
import com.unique.app.community.utils.TimeUtils;
import com.unique.app.community.widget.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/21/16.
 */
public class DisplayEventViewHolder extends BaseViewHolder<Event> {

    @BindView(R.id.display_item_image)
    ImageView displayItemImage;
    @BindView(R.id.display_item_title_text)
    TextView displayItemTitleText;
    @BindView(R.id.display_item_sponsor_image)
    CircularImageView displayItemSponsorImage;
    @BindView(R.id.display_item_tags_container)
    LinearLayout displayItemTagsContainer;
    @BindView(R.id.display_item_activity_time_text)
    TextView displayItemActivityTimeText;
    @BindView(R.id.display_item_position_text)
    TextView displayItemPositionText;
    @BindView(R.id.display_item_person_text)
    TextView displayItemPersonText;
    @BindView(R.id.display_item_release_time_text)
    TextView displayItemReleaseTimeText;

    private DisplayEventsFragment fragment;

    public DisplayEventViewHolder(ViewGroup parent, DisplayEventsFragment fragment) {
        super(parent, R.layout.item_display_events);
        this.fragment = fragment;
        ButterKnife.bind(this,itemView);
    }


    @Override
    public void bindData(Event data) {
        parseImage(data);
        parseTitle(data);
        parseSponsorImage(data);
        parseText(data);
        parseTags(data);
    }

    private void parseTitle(Event event) {
        displayItemTitleText.setText(event.getSubject());
    }

    private void parseImage(Event event) {
        if (event.getImage().size() == 0){
            return;
        }
        Glide.with(fragment).load(event.getImage().get(0))
                .error(R.mipmap.need_to_remove)
                .into(displayItemImage);
    }

    private void parseSponsorImage(Event event) {
        if (event.getSponsor().getAvatat() == null){
            return;
        }
        Glide.with(fragment).load(event.getSponsor().getAvatat().getUrl())
                .error(R.mipmap.default_avatar).into(displayItemSponsorImage);
    }

    private void parseText(Event event) {
        displayItemPositionText.setText(event.getPlace());
        //displayItemPersonText.setText(String.format("当前人数(%d/%d)", event.getParticipation().size(), event.getExcepted()));
        displayItemActivityTimeText.setText(TimeUtils.parseTime(event.getTime()));
        displayItemReleaseTimeText.setText(TimeUtils.parseTime(event.getCreatedAt()));
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
                        View view = Inflater.inflate(R.layout.item_display_event_tag,displayItemTagsContainer,false);
                        TextView text = (TextView) view.findViewById(R.id.display_item_event_tag);
                        text.setText(tag);
                        displayItemTagsContainer.addView(view);
                    }
                });



    }

    @OnClick(R.id.display_item_root)
    void item_root(){
        //TODO:进入event展示界面
    }



}
