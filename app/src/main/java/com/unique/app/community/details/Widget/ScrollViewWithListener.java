package com.unique.app.community.details.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/5.
 */

public class ScrollViewWithListener extends ScrollView {

    private OnScrollListener scrollListener;

    public ScrollViewWithListener(Context context) {
        super(context);
    }

    public ScrollViewWithListener(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewWithListener(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onScrollChanged(int x, int y, int oldX, int oldY){
        super.onScrollChanged(x, y, oldX, oldY);
        if(scrollListener != null){
            scrollListener.onScroll(y, oldY);
        }
    }

    public void setScrollListener(OnScrollListener scrollListener){
        this.scrollListener = scrollListener;
    }


    public interface OnScrollListener{
        void onScroll(int y, int oldY);
    }

}
