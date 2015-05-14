package com.cyd.pagertab_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by dong on 2015/5/8.
 */
public class TabLayout extends LinearLayout {
    public final static  int MODE_OUTLINE = 0;
    public final static  int MODE_ALPHA= 1;
    public final static  int MODE_BLOCK = 2;
    int position = 0;
    int childCount = 4;
    int strokeWidth = 2;
    int blockHeight = 10;
    float progress;
    int outLineColor = Color.CYAN;
    int alphaBgColor = Color.CYAN;
    int blockColor = Color.CYAN;
    int mode = MODE_OUTLINE;
    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }


    public void init(Context context, AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TabLayout);
        childCount = array.getInt(R.styleable.TabLayout_child_count, childCount);
        outLineColor = array.getColor(R.styleable.TabLayout_outline_color, outLineColor);
        alphaBgColor =array.getColor(R.styleable.TabLayout_alphabg_color, alphaBgColor);
        blockColor = array.getColor(R.styleable.TabLayout_block_color, blockColor);

        strokeWidth = array.getDimensionPixelOffset(R.styleable.TabLayout_strock_width, strokeWidth);
        mode = array.getInt(R.styleable.TabLayout_mode,mode);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int parentWidth = canvas.getWidth();
        int width = parentWidth / childCount;
        int height = canvas.getHeight();
        int smallRectW = width /10;
        int radius = smallRectW / 2;
        int left = (int) (width * (position + progress));
        //        outline
        if(mode == MODE_OUTLINE) {

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(outLineColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);


            canvas.drawLine(left, radius, left, height, paint);
            canvas.drawLine(left + radius, 0, left + width - radius, 0, paint);
            canvas.drawLine(left + width, radius, left + width, height, paint);

            RectF rectF = new RectF();
            rectF.set(left, 0, left + smallRectW, smallRectW);
            canvas.drawArc(rectF, 180, 90, false, paint);

            rectF.set(left + width - smallRectW, 0, left + width, smallRectW);
            canvas.drawArc(rectF, 270, 90, false, paint);

            canvas.drawLine(0, height - strokeWidth, left, height - strokeWidth, paint);
            canvas.drawLine(left + width, height - strokeWidth, parentWidth, height - strokeWidth, paint);
        }
//      alpha background
        if(mode == MODE_ALPHA) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(alphaBgColor);
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

//      flow block
        if(mode == MODE_BLOCK){

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(blockColor);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);

            Rect rect = new Rect();
            rect.set(left , height - blockHeight,left + width ,height);
            canvas.drawRect(rect,paint);
        }

    }

    public void setAlphaBgColor(int alphaBgColor) {
        this.alphaBgColor = alphaBgColor;
    }

    public void setOutLineColor(int outLineColor) {
        this.outLineColor = outLineColor;
    }

    public void setBlockColor(int blockColor) {
        this.blockColor = blockColor;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public  void setMode(int mode){
        this.mode = mode;
    }

    public  void setChildCount(int childCount){
        this.childCount= childCount;

    }
    public void  setStrokeWidth(int strokeWidth){
        this.strokeWidth = strokeWidth;
    };

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
