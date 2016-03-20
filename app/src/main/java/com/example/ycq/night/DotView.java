package com.example.ycq.night;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ycg on 2016-03-04.
 */
public class DotView extends View {
    private Dots dots;
    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDots(Dots dots) {
        this.dots = dots;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if(null != dots) {
            for (Dot dot : dots.getDots()) {
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getX(), dot.getY(), dot.getDiameter(), paint);
            }
        }
    }
}
