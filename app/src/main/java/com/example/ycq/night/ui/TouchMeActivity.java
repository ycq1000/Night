package com.example.ycq.night.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ycq.night.Dot;
import com.example.ycq.night.DotView;
import com.example.ycq.night.Dots;
import com.example.ycq.night.R;

import java.util.Random;

public class TouchMeActivity extends AppCompatActivity {
    public static final int DOT_DIAMETER = 40;
    private final Random rand = new Random();
    final Dots dotModel = new Dots();
    private DotView dotView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_me);
        this.dotView = (DotView) findViewById(R.id.dotView);
        this.dotView.setDots(dotModel);
        ((Button) findViewById(R.id.red)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                makeDot(dotModel, dotView, Color.RED);
            }
        });

        ((Button) findViewById(R.id.green)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                makeDot(dotModel, dotView, Color.GREEN);
            }
        });

        ((Button) findViewById(R.id.clear)).setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                dotModel.clearDots();
                dotView.invalidate();
            }
        });

        final TextView tbx = (TextView) findViewById(R.id.x);
        final TextView tby = (TextView) findViewById(R.id.y);
        dotModel.setDotsChangeListener(new Dots.DotsChangeListener(){
            @Override
            public void onDotsChange(Dots dots) {
                Dot dot = dots.getLastDot();
                tbx.setText((null == dot) ? "" : String.valueOf(dot.getX()));
                tby.setText((null == dot) ? "" : String.valueOf(dot.getY()));
                dotView.invalidate();
            }
        });

        dotView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //dotModel.addDot(event.getX(), event.getY(), Color.BLUE, DOT_DIAMETER);
                        for(int i = 0, n = event.getHistorySize(); i < n; i++) {
                            dotModel.addDot(
                                    event.getHistoricalX(i),
                                    event.getHistoricalY(i),
                                    Color.BLUE, DOT_DIAMETER);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


    }
    void makeDot(Dots dots, DotView view, int color) {
        int pad = (DOT_DIAMETER + 2) * 2;
        dotModel.addDot(
                DOT_DIAMETER + (rand.nextFloat() * view.getWidth() - pad),
                DOT_DIAMETER + (rand.nextFloat() * (view.getHeight()) - pad),
                color,
                DOT_DIAMETER
        );
    }
}
