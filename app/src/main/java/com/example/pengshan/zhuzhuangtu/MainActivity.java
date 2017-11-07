package com.example.pengshan.zhuzhuangtu;

import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyViewFlipper.OnMyMoveListern {

    private RelativeLayout flipper;
    int[] data1 = {300, 500, 550, 500, 300, 700, 800, 750, 550, 600, 400, 300, 400, 600, 500,
            700, 300, 500, 550, 500, 300, 700, 800, 750, 550, 600, 400, 300, 400, 600, 500};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main);
        flipper = (RelativeLayout) findViewById(R.id.vf);
        initBarChart1();
    }

    /**
     * 初始化柱状图数据
     */
    private void initBarChart1() {

        List<int[]> data = new ArrayList<>();
        data.add(data1);
        CustomBarChart customBarChart = new CustomBarChart(this, 1500, data);
        flipper.addView(customBarChart);
//        flipper.setOnMyMoveListern(this);
    }


    @Override
    public void move() {
        flipper.removeAllViews();
        List<int[]> data = new ArrayList<>();
        data.add(data1);
        flipper.addView(new CustomBarChart(this, 1500, data));
    }
}
