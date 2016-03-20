package com.example.ycq.night.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.example.ycq.night.R;

/**
 * Created by qiang on 2015-12-14.
 */
public class SingleRowRoundTextView extends TextView {
    private int borderColor = Color.TRANSPARENT;
    private int backgroundColor = Color.TRANSPARENT;
    Paint paint;

    public SingleRowRoundTextView(Context context) {
        super(context);
        this.initUI();
    }

    public SingleRowRoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleRowRoundView);

        this.backgroundColor = typedArray.getColor(R.styleable.SingleRowRoundView_custom_backColor, Color.TRANSPARENT);
        this.borderColor = typedArray.getColor(R.styleable.SingleRowRoundView_custom_borderColor, Color.TRANSPARENT);
        this.initUI();
    }

    private void initUI() {
        this.setSingleLine(true);
        this.setEllipsize(TextUtils.TruncateAt.END);
        this.setGravity(Gravity.CENTER);
        this.setPadding(0, 0, 0, 0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(null);
        } else {
            this.setBackgroundDrawable(null);
        }
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(this.backgroundColor != Color.TRANSPARENT) {
            if(this.paint == null) {
                this.paint = new Paint();
            }
            this.paint.setColor(this.backgroundColor);
            this.paint.setStyle(Style.FILL);
            this.paint.setAntiAlias(true);
            RectF rectF = new RectF();
            rectF.left = getScrollX();
            rectF.top = getScrollY();
            rectF.right = rectF.left + this.getMeasuredWidth();
            rectF.bottom = rectF.top + this.getMeasuredHeight();
            float r = rectF.bottom / 2;
            canvas.drawRoundRect(rectF, r, r, paint);
        }
        super.onDraw(canvas);
        if(this.borderColor != Color.TRANSPARENT) {
            if(this.paint == null) {
                this.paint = new Paint();
            }
            this.paint.setColor(this.borderColor);
            this.paint.setStyle(Style.STROKE);
            this.paint.setAntiAlias(true);
            RectF rectF = new RectF();
            rectF.left = getScrollX();
            rectF.top = getScrollY();
            rectF.right = rectF.left + this.getMeasuredWidth();
            rectF.bottom = rectF.top + this.getMeasuredHeight();
            float r = rectF.bottom / 2;
            canvas.drawRoundRect(rectF, r, r, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int adjustMeasuredHeight = this.getMeasuredHeight();
        int padding = Math.round(adjustMeasuredHeight * 0.4f);
        this.setPadding(padding, 0, padding, 0);
        /*heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                adjustMeasuredHeight, MeasureSpec.EXACTLY);*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
