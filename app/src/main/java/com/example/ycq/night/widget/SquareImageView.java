package com.example.ycq.night.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by qiang on 2015-12-14.
 */
public class SquareImageView extends ImageView {
    /**
     * 长宽比
     */
    private float ratio;
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewSize = this.getMeasuredWidth();
        // 高度和宽度一样
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(viewSize, MeasureSpec.EXACTLY);
        if(this.ratio <= 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewSize, MeasureSpec.EXACTLY);
        } else {
            int adjustHeight = Math.round(viewSize / this.ratio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(adjustHeight, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}