package com.unique.app.community.details.Widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Author: Alexander
 * Email: yifengtang@hustunique.com
 * Since: 16/11/4.
 */

public class ViewPagerInScrollView extends ViewPager {

    public ViewPagerInScrollView(Context context) {
        super(context);
    }

    public ViewPagerInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if(getChildCount() != 0) {
            View child = getChildAt(getCurrentItem());
            child.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = child.getMeasuredHeight();
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
