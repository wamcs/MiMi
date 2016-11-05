package com.unique.app.community.details.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/4.
 */

public class KeyboardListenerLayout extends RelativeLayout {

    private OnSizeChangeListener listener;

    public KeyboardListenerLayout(Context context) {
        super(context);
    }

    public KeyboardListenerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardListenerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH){
        super.onSizeChanged(w, h, oldW, oldH);
        if(listener != null){
            listener.onSizeChange(w, h, oldW, oldH);
        }
    }

    public interface OnSizeChangeListener{
        void onSizeChange(int w, int h, int oldW, int oldH);
    }

    public void setOnSizeChangeListener(OnSizeChangeListener listener){
        this.listener = listener;
    }

}
