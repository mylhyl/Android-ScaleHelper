package com.mylhyl.scalehelper.sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mylhyl.scalehelper.ScaleHelper;

/**
 * 随机码
 * Created by hupei on 2016/5/25.
 */
public class RandomCodeView extends View {
    private static final String TAG = "CustomView";
    private Rect mRect;
    private Paint mPaint;
    private String mText;
    private int textSize;

    public RandomCodeView(Context context) {
        super(context);
    }

    public RandomCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public RandomCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RandomCodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initPaint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (isInEditMode()) return;
//        mText = "1234";
//        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 40, getResources().getDisplayMetrics());
        textSize = 40;

        mPaint = new Paint();
        mRect = new Rect();

        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.getTextBounds(mText, 0, mText.length(), mRect);
    }


    public void setTextSize(int textSize) {
//        this.textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics());
        this.textSize = textSize;
        postInvalidate();
        requestLayout();
    }

    public void setText(String mText) {
        this.mText = mText;
        postInvalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isInEditMode()) return;
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(Color.RED);
//        //居中画文字
        canvas.drawText(mText, (getWidth() - mRect.width() - getPaddingLeft()) / 2, (getHeight() + mRect.height()) / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) return;
        int width;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {//match_parent
            width = widthSize;
        } else {//wrap_content
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            int textWidth = mRect.width();
            int paddingLeft = getPaddingLeft() / 2;
            int paddingRight = getPaddingRight() / 2;
            width = textWidth + paddingLeft + paddingRight;
        }

        int height;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mRect);
            int textHeight = mRect.height();
            height = textHeight + getPaddingTop() / 2 + getPaddingBottom() / 2;
        }
        Log.d(TAG, "onMeasure: width=" + width);
        setMeasuredDimension(width, height);

//        int scaleWidth = ScaleHelper.scaleValue(width);
//        Log.d(TAG, "onMeasure: scaleWidth=" + scaleWidth);
//        setMeasuredDimension(scaleWidth, height);
    }
}
