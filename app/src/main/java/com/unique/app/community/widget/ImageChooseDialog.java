package com.unique.app.community.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avos.avoscloud.AVFile;
import com.unique.app.community.R;
import com.unique.app.community.global.Conf;
import com.unique.app.community.permission.PermissionDispatcher;
import com.unique.app.community.permission.TargetPermission;
import com.unique.app.community.utils.DirUtil;
import com.unique.app.community.utils.Display;

import java.io.File;
import java.lang.annotation.Retention;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 10/30/16.
 */
public class ImageChooseDialog extends Dialog {

    private File mTempFile;
    private OnImageChosenListener mListener;
    private Fragment fragment;
    private Activity activity;
    private Context context;


    public ImageChooseDialog(Fragment fragment) {
        super(fragment.getContext());
        this.context = fragment.getContext();
        this.fragment = fragment;
        init();
    }

    public ImageChooseDialog(AppCompatActivity activity) {
        super(activity);
        this.context = activity;
        this.activity = activity;
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_image_choose);
        View view = getWindow().getDecorView();
        ButterKnife.bind(this, view);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = Display.width();
        layoutParams.height = (int) (Display.height() * 0.3f);
        window.setAttributes(layoutParams);
        window.setGravity(Gravity.BOTTOM);
    }


    @Override
    public void show() {
        if (null == mTempFile) {
            mTempFile = new File(DirUtil.getResDirectory(), Conf.TEMP_IMAGE_NAME);
        }
        if (mTempFile.exists()) {
            Timber.d("=====tempFile exist");
        } else {
            Timber.d("=====tempFile not exist");
        }
        super.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {

            case Conf.REQUEST_CODE_TAKE_PHOTO:
                startCutPhoto(Uri.fromFile(mTempFile), Conf.USER_AVATAR_SIZE, Conf.USER_AVATAR_SIZE);
                break;

            case Conf.REQUEST_CODE_GALLERY:
                if (null != data) {
                    startCutPhoto(data.getData(), Conf.USER_AVATAR_SIZE, Conf.USER_AVATAR_SIZE);
                }
                break;

            case Conf.REQUEST_CODE_ALBUM:
                if (null != data){
                    startCutPhoto(data.getData(), Conf.USER_AVATAR_SIZE, Conf.USER_AVATAR_SIZE);
                }
                break;

            case Conf.REQUEST_CODE_CUT_PHOTO:
                if (null != data) {
                    mListener.onImageChoosed(mTempFile);
                }
                break;
        }
    }

    private void startCutPhoto(Uri uri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempFile));
        if (fragment != null) {
            fragment.startActivityForResult(intent, Conf.REQUEST_CODE_CUT_PHOTO);
        } else if (activity != null) {
            activity.startActivityForResult(intent, Conf.REQUEST_CODE_CUT_PHOTO);
        }
    }

    @OnClick(R.id.image_choose_dialog_phone_album)
    void startPhoneAlbum() {
        if (fragment!=null){
            Intent intent = new Intent(fragment.getContext(),PhoneAlbumActivity.class);
            fragment.startActivityForResult(intent,Conf.REQUEST_CODE_ALBUM);
        }else if (activity!=null){
            Intent intent = new Intent(activity,PhoneAlbumActivity.class);
            activity.startActivityForResult(intent,Conf.REQUEST_CODE_ALBUM);
        }
    }

    @OnClick(R.id.image_choose_dialog_take_phone)
    void startTakePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempFile));
        if (fragment != null) {
            fragment.startActivityForResult(intent, Conf.REQUEST_CODE_TAKE_PHOTO);
        } else if (activity != null) {
            activity.startActivityForResult(intent, Conf.REQUEST_CODE_TAKE_PHOTO);
        }
        dismiss();
    }

    @OnClick(R.id.image_choose_dialog_system_gallery)
    void startSystemGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, null);
        galleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        if (fragment != null) {
            fragment.startActivityForResult(galleryIntent, Conf.REQUEST_CODE_GALLERY);
        } else if (activity != null) {
            activity.startActivityForResult(galleryIntent, Conf.REQUEST_CODE_GALLERY);
        }
        dismiss();
    }

    @OnClick(R.id.image_choose_dialog_canal)
    void close(){
        dismiss();
    }

    public interface OnImageChosenListener {
        void onImageChoosed(File file);
    }

    public void setOnImageChoosedListener(OnImageChosenListener listener) {
        mListener = listener;
    }
}
