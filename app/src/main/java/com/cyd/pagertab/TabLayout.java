package com.cyd.pagertab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by dong on 2015/5/8.
 */
public class TabLayout extends LinearLayout {
    int position = 0;
    int childCount = 4;
    int smallRectW = 20;
    int strokeWidth = 1;
    float progress;

    public TabLayout(Context context) {
        super(context);
        init();
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int parentWidth = canvas.getWidth();
        int width = parentWidth / childCount;
        int height = canvas.getHeight();
//        ±ß¿ò
//        int radius = smallRectW / 2;
//        int left = (int) (width * (position + progress));


//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.BLACK);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(strokeWidth);


//        canvas.drawLine(left, radius, left, height, paint);
//        canvas.drawLine(left + radius, 0, left + width - radius, 0, paint);
//        canvas.drawLine(left + width, radius, left + width, height, paint);
//
//        RectF rectF = new RectF();
//        rectF.set(left, 0, left + smallRectW, smallRectW);
//        canvas.drawArc(rectF, 180, 90, false, paint);
//
//        rectF.set(left + width - smallRectW, 0, left + width, smallRectW);
//        canvas.drawArc(rectF, 270, 90, false, paint);
//
//        canvas.drawLine(0, height - strokeWidth, left, height - strokeWidth, paint);
//        canvas.drawLine(left + width, height - strokeWidth, parentWidth, height - strokeWidth, paint);

//        Í¸Ã÷¶È½¥±ä±³¾°
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);


        int left1 = position * width;
        Rect rect1 = new Rect();
        rect1.set(left1, 0, left1 + width, height);
        paint.setAlpha((int) (255 * (1 - progress)));
        canvas.drawRect(rect1, paint);

        int left2 = (position + 1) * width;
        Rect rect2 = new Rect();
        rect2.set(left2, 0, left2 + width, height);
        paint.setAlpha((int) (225 * progress));
        canvas.drawRect(rect2, paint);


    }


    public void setPosition(int position) {
        this.position = position;
        invalidate();
    }

    public void setProgress(int position, float progress) {
        this.position = position;
        this.progress = progress;
        invalidate();

    }

}
