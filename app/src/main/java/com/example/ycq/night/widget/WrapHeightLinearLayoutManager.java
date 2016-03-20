package com.example.ycq.night.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiang on 2015-12-22.
 */
public class WrapHeightLinearLayoutManager extends LinearLayoutManager {
    public WrapHeightLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);

        if(getOrientation() == OrientationHelper.HORIZONTAL) {
            super.onMeasure(recycler, state, widthSpec, heightSpec);
            return;
        }

        int heightMode = View.MeasureSpec.getMode(heightSpec);
        int heightSize = View.MeasureSpec.getSize(heightSpec);


        int childCount = this.getItemCount();
        android.util.Log.e("ycq", "l:"+childCount);
        heightSize = 0;
        for(int i = 0; i < childCount; i++) {
            heightSize += this.measureScrapChild(recycler, i);
        }

        android.util.Log.e("ycq", "recylcerPadding:"+getPaddingTop()+","+getPaddingBottom());
        heightSize += getPaddingTop() + getPaddingBottom();
        heightMode = View.MeasureSpec.EXACTLY;
        heightSpec = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

    }

    private int measureScrapChild(RecyclerView.Recycler recycler, int position) {
        View childView = recycler.getViewForPosition(position);
        if (childView != null) {
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int childWidthSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), 0, p.width);

            int childHeightSpec = ViewGroup.getChildMeasureSpec(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    0, p.height);
            childView.measure(childWidthSpec, childHeightSpec);
            recycler.recycleView(childView);


            int top = 0;
            top = getDecoratedTop(childView) + getDecoratedBottom(childView);
            android.util.Log.e("ycq", "top:"+top);

            android.util.Log.e("ycq", "h:"+p.topMargin+","+p.bottomMargin+","+top+","+childView.getBottom()+","+getDecoratedBottom(childView)+","+
                    childView.getMeasuredHeight()+","+getDecoratedMeasuredHeight(childView)+","+getBottomDecorationHeight(childView)+","+getTopDecorationHeight(childView));

            return childView.getMeasuredHeight() + p.bottomMargin + p.topMargin + top;
        }
        return 0;
    }
}
