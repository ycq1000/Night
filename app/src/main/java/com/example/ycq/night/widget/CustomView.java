package com.example.ycq.night.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ycg on 2016-03-10.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        android.util.Log.e("ycq", "onDraw");
        int width = getMeasuredWidth();
        int height=getMeasuredHeight();
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        canvas.drawRect(0,0,width,height, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*android.util.Log.e("ycq", "onMeasure:"+MeasureSpec.getMode(widthMeasureSpec)+":"+MeasureSpec.getSize(widthMeasureSpec)+
        ","+getSuggestedMinimumWidth()+","+getSuggestedMinimumHeight());
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.AT_MOST) {
            android.util.Log.e("ycq","mode:AT_MOST");
        } else if(mode == MeasureSpec.EXACTLY) {
            android.util.Log.e("ycq","mode:EXACTLY");
        } else if(mode == MeasureSpec.UNSPECIFIED) {
            android.util.Log.e("ycq","mode:UNSPECIFIED");
        }*/
    }
}
