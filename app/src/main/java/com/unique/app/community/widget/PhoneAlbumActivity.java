package com.unique.app.community.widget;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.unique.app.community.R;
import com.unique.app.community.global.Conf;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 11/8/16.
 */
public class PhoneAlbumActivity extends AppCompatActivity {

    @BindView(R.id.image_choose_dialog_phone_album_gridview)
    GridView mGridview;

    private ArrayList<HashMap<String,Integer>> items;
    private static final String ITEMIMAGE = "itemImage";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_album);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        //TODO:finish items
        SimpleAdapter adapter = new SimpleAdapter(this,items,R.layout.item_phone_album,
                new String[]{ITEMIMAGE},new int[]{R.id.image_choose_dialog_phone_album_gridview_item});
        mGridview.setAdapter(adapter);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                //TODO:set data
                Resources r=getResources();
                int id = items.get(i).get(ITEMIMAGE);
                Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(id) + "/"
                + r.getResourceTypeName(id) + "/"
                + r.getResourceEntryName(id));

                intent.setData(uri);
                setResult(Conf.REQUEST_CODE_ALBUM,intent);
                finish();
            }
        });


    }
}
