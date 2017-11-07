package com.example.pengshan.zhuzhuangtu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CustomBarChart extends View {

    private  int yMax;
    // 坐标单位
//    private String[] xLabel;
    private String[] yLabel;
    // 曲线数据
    private List<int[]> dataList;
    // 默认边距
    private int margin = 20;
    // 距离左边偏移量
    private int marginX = 15;
    // 原点坐标
    private int xPoint;
    private int yPoint;
    // X,Y轴的单位长度
    private int xScale;
    private int yScale;
    // 画笔
    private Paint paintAxes;
    private Paint paintCoordinate;
    private Paint paintRectF;
    private Paint paintValue;
    private Integer barColor = R.color.green;
    private Integer backColor = R.color.colorBack;
    private Paint paintBackRectF;
    String[] xLabel = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
            "28", "29", "30", "31"};
    private Paint paintDoLine;
    private Paint mDashPaint;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private int shixian = 9;
    private int xuxian = 5;
    private int mCishu;

    public CustomBarChart(Context context,int yMax,
                          List<int[]> dataList) {
        super(context);
//        this.xLabel = xLabel;
        this.yMax = yMax;
        this.yLabel = new String[]{"0",String.valueOf(yMax/2),String.valueOf(yMax)};
        this.dataList = dataList;
    }

    public CustomBarChart(Context context) {
        super(context);
    }

    /**
     * 初始化数据值和画笔
     */
    public void init() {
        xPoint = margin + marginX;
        yPoint = this.getHeight() - margin;
        xScale = (this.getWidth() - 2 * margin - marginX) / (xLabel.length - 1);
        yScale = (this.getHeight() - 2 * margin) / (yLabel.length - 1);
 
        /*paintAxes = new Paint();
        paintAxes.setStyle(Paint.Style.STROKE);
        paintAxes.setAntiAlias(true);
        paintAxes.setDither(true);
        paintAxes.setColor(ContextCompat.getColor(getContext(), R.color.color11));
        paintAxes.setStrokeWidth(4);*/

        paintCoordinate = new Paint();
        paintCoordinate.setStyle(Paint.Style.STROKE);
        paintCoordinate.setDither(true);
        paintCoordinate.setAntiAlias(true);
        paintCoordinate.setColor(ContextCompat.getColor(getContext(), R.color.color11));
        paintCoordinate.setTextSize(20);

        paintRectF = new Paint();
        paintRectF.setStyle(Paint.Style.FILL);
        paintRectF.setDither(true);
        paintRectF.setAntiAlias(true);
        paintRectF.setStrokeWidth(1);

        paintDoLine = new Paint();
        paintDoLine.setStyle(Paint.Style.FILL);
        paintDoLine.setDither(true);
        paintDoLine.setAntiAlias(true);
        paintDoLine.setColor(ContextCompat.getColor(getContext(), R.color.color11));
        paintDoLine.setStrokeWidth(1);

        paintBackRectF = new Paint();
        paintBackRectF.setStyle(Paint.Style.FILL);
        paintBackRectF.setDither(true);
        paintBackRectF.setAntiAlias(true);
        paintBackRectF.setStrokeWidth(1);

        mDashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDashPaint.reset();
        mDashPaint.setStyle(Paint.Style.STROKE);
        mDashPaint.setColor(Color.RED);
        mDashPaint.setStrokeWidth(1f);
  /*      paintValue = new Paint();
        paintValue.setStyle(Paint.Style.STROKE);
        paintValue.setAntiAlias(true);
        paintValue.setDither(true);
        paintValue.setTextAlign(Paint.Align.CENTER);
        paintValue.setTextSize(20);*/
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        mMeasuredWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mMeasuredHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        mCishu = mMeasuredWidth / (shixian + xuxian);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.color1));
        init();
//        drawAxesLine(canvas, paintAxes);
        drawCoordinate(canvas, paintCoordinate);
        if (dataList.size() == 1) {
            drawBar(canvas, paintRectF,paintBackRectF, dataList.get(0), barColor,backColor);
//            drawValue(canvas, paintValue, dataList.get(0), colorList.get(2));
        }/* else if (dataList.size() == 2) {
            drawBars(canvas, paintRectF, dataList, barColor);
//            drawValues(canvas, paintValue, dataList, colorList.get(2));
        }*/
//        drawHorizontalLine(canvas);
        drawDashView(canvas,mDashPaint);
    }

    private void drawDashView(Canvas canvas,Paint mDashPaint) {
        float startX;
        float endX = 0;
        float startY = mMeasuredHeight / 2;
        float endY = startY;
        for (int i = 0; i < mCishu; i++) {
            if(i <= 0){
                startX = xPoint + xScale;
            }else {
                startX = endX + xuxian;
            }
            endX = startX + shixian;
            canvas.drawLine(startX,startY,endX,endY,mDashPaint);
        }
    }

    /**
     * 绘制坐标轴
     */
/*    private void drawAxesLine(Canvas canvas, Paint paint) {
        // X
        canvas.drawLine(xPoint, yPoint, this.getWidth() - margin / 6, yPoint, paint);
        canvas.drawLine(this.getWidth() - margin / 6, yPoint, this.getWidth() - margin / 2, yPoint - margin / 3, paint);
        canvas.drawLine(this.getWidth() - margin / 6, yPoint, this.getWidth() - margin / 2, yPoint + margin / 3, paint);
 
        // Y
        canvas.drawLine(xPoint, yPoint, xPoint, margin / 6, paint);
        canvas.drawLine(xPoint, margin / 6, xPoint - margin / 3, margin / 2, paint);
        canvas.drawLine(xPoint, margin / 6, xPoint + margin / 3, margin / 2, paint);
    }*/


 /**
     * 画水平方向虚线
     * @param canvas
     */
    /*public void drawHorizontalLine(Canvas canvas){
        float totalWidth = 0;
        canvas.save();
        float lineWidth = this.getWidth() ;
        float[] pts = {0,0,lineWidth,0};
        //在画线之前需要先把画布向下平移办个线段高度的位置，目的就是为了防止线段只画出一半的高度
        //因为画线段的起点位置在线段左下角
        int lineHeight = 2;
        int dashWidth = 10;
        canvas.translate(0,lineHeight/2);
        while(totalWidth <= this.getWidth()){
            canvas.drawLines(pts,paintDoLine);
            canvas.translate(lineWidth + dashWidth,0);
            totalWidth += lineWidth + dashWidth;
        }
        canvas.restore();
    }*/
    /**
     * 绘制刻度
     */
    private void drawCoordinate(Canvas canvas, Paint paint) {
        // X轴坐标
        for (int i = 0; i <= (xLabel.length - 1); i++) {
            if ((i > 0 && i % 5 == 0) || i == 1) {
                paint.setTextAlign(Paint.Align.CENTER);
                int startX = xPoint + i * xScale;
                canvas.drawText(xLabel[i], startX, this.getHeight() - margin / 6, paint);
            }
        }

        // Y轴坐标
        for (int i = 0; i <= (yLabel.length - 1); i++) {
            paint.setTextAlign(Paint.Align.LEFT);
            int startY = yPoint - i * yScale;
            int offsetX;
            switch (yLabel[i].length()) {
                case 1:
                    offsetX = 28;
                    break;

                case 2:
                    offsetX = 20;
                    break;

                case 3:
                    offsetX = 12;
                    break;

                case 4:
                    offsetX = 5;
                    break;

                default:
                    offsetX = 0;
                    break;
            }
            int offsetY;
            if (i == 0) {
                offsetY = 0;
            } else {
                offsetY = margin / 5;
            }
            canvas.drawText(yLabel[i], margin / 4 + offsetX, startY + offsetY, paint);
        }
    }
    float endY = 0;
    /**
     * 绘制单柱形
     */
    private void drawBar(Canvas canvas, Paint paint,Paint paintBackRectF, int data[], Integer barColor,Integer backColor) {
        for (int i = 1; i <= (xLabel.length - 1); i++) {
            int startX = xPoint + i * xScale;
            RectF rect1 = new RectF(startX - 8,0 , startX + 8, this.getHeight() - margin - 15);
            RectF rect = new RectF(startX - 8, toY(data[i - 1]), startX + 8, this.getHeight() - margin - 15);
//            if (i % 2 == 1) {
                paint.setColor(ContextCompat.getColor(getContext(), barColor));
//            } else {
//                paint.setColor(ContextCompat.getColor(getContext(), barColor));
//            }
          /*  float[] positions = {0,(float) (this.getHeight() - margin - 10)/2};
            int[] colors = {,};*/
            LinearGradient lg = new LinearGradient(startX-8,this.getHeight() - margin - 15,startX + 8,
                    toY(data[i - 1]), getResources().getColor(R.color.blue),getResources().getColor(R.color.green), Shader.TileMode.CLAMP);
            paint.setShader(lg);
            paintBackRectF.setColor(ContextCompat.getColor(getContext(), backColor));
            canvas.drawRoundRect(rect1, 10, 10, paintBackRectF);
            canvas.drawRoundRect(rect, 10, 10, paint);

        }
//
    }

    /**
     * 绘制双柱形
     */
/*    private void drawBars(Canvas canvas, Paint paint, List<int[]> dataList, Integer barColor) {
        for (int i = 1; i <= (xLabel.length - 1); i++) {
            int startX = xPoint + i * xScale;
            paint.setColor(ContextCompat.getColor(getContext(), barColor));
            RectF rect1 = new RectF(startX - 20, toY(dataList.get(0)[i - 1]), startX - 10,
                    this.getHeight() - margin - 2);
            canvas.drawRect(rect1, paint);
 
            paint.setColor(ContextCompat.getColor(getContext(), barColor));
            RectF rect2 = new RectF(startX - 5, toY(dataList.get(1)[i - 1]), startX + 5,
                    this.getHeight() - margin - 2);
            canvas.drawRect(rect2, paint);
        }
    }*/

    /**
     * 绘制单数值
     */
/*    private void drawValue(Canvas canvas, Paint paint, int data[], int color) {
        paint.setColor(ContextCompat.getColor(getContext(), color));
        for (int i = 1; i <= (xLabel.length - 1); i++) {
            canvas.drawText(data[i - 1] + "w", xPoint + i * xScale, toY(data[i - 1]) - 5, paintValue);
        }
    }*/

    /**
     * 绘制双数值
     */
 /*   private void drawValues(Canvas canvas, Paint paint, List<int[]> dataList, int color) {
        paint.setColor(ContextCompat.getColor(getContext(), color));
        for (int i = 1; i <= (xLabel.length - 1); i++) {
            int startX = xPoint + i * xScale;
            int offsetY1 = 5;
            int offsetY2 = 5;
            if (dataList.get(0)[i - 1] == dataList.get(1)[i - 1]) {
                offsetY2 += 10;
            }
            if (i > 1) {
                if ((dataList.get(1)[i - 2] == dataList.get(0)[i - 1])) {
                    offsetY1 += 10;
                }
            }
            canvas.drawText(dataList.get(0)[i - 1] + "w", startX - 18,
                    toY(dataList.get(0)[i - 1]) - offsetY1, paintValue);
            canvas.drawText(dataList.get(1)[i - 1] + "w", startX + 3,
                    toY(dataList.get(1)[i - 1]) - offsetY2, paintValue);
        }
    }*/

    /**
     * 数据按比例转坐标
     */
    private float toY(int num) {
        float y;
        try {
            float a = (float) num / yMax * 1f;
            y = yPoint - a * (this.getHeight() - 2 * margin);
        } catch (Exception e) {
            return 0;
        }
        return y;
    }

}