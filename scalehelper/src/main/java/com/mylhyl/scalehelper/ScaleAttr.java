package com.mylhyl.scalehelper;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hupei on 2016/3/8.
 */
public class ScaleAttr {

    private static final int[] LL = new int[]
            {
                    android.R.attr.textSize,
                    android.R.attr.padding,
                    android.R.attr.paddingLeft,
                    android.R.attr.paddingTop,
                    android.R.attr.paddingRight,
                    android.R.attr.paddingBottom,
                    android.R.attr.layout_width,
                    android.R.attr.layout_height,
                    android.R.attr.layout_margin,
                    android.R.attr.layout_marginLeft,
                    android.R.attr.layout_marginTop,
                    android.R.attr.layout_marginRight,
                    android.R.attr.layout_marginBottom,
                    android.R.attr.maxWidth,
                    android.R.attr.maxHeight,
                    android.R.attr.minWidth,
                    android.R.attr.minHeight,
                    android.R.attr.drawablePadding,
            };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;
    private static final int INDEX_DRAWABLE_PADDING = 17;


    public static void scaleView(final View view, AttributeSet attrs) {
        if (view == null) return;
        TypedArray array = view.getContext().obtainStyledAttributes(attrs, LL);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);
            if (!isPxVal(array.peekValue(index))) continue;

            int valPx;
            try {
                valPx = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore) {//not dimension
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    if (view instanceof TextView) ScaleHelper.scaleTextSize((TextView) view, valPx);
                    break;
                case INDEX_PADDING:
                    ScaleHelper.scalePadding(view, valPx);
                    break;
                case INDEX_PADDING_LEFT:
                    ScaleHelper.scalePaddingLeft(view, valPx);
                    break;
                case INDEX_PADDING_TOP:
                    ScaleHelper.scalePaddingTop(view, valPx);
                    break;
                case INDEX_PADDING_RIGHT:
                    ScaleHelper.scalePaddingRight(view, valPx);
                    break;
                case INDEX_PADDING_BOTTOM:
                    ScaleHelper.scalePaddingBottom(view, valPx);
                    break;
                case INDEX_WIDTH:
                    break;
                case INDEX_HEIGHT:
                    break;
                case INDEX_MARGIN:
                    break;
                case INDEX_MARGIN_LEFT:
                    break;
                case INDEX_MARGIN_TOP:
                    break;
                case INDEX_MARGIN_RIGHT:
                    break;
                case INDEX_MARGIN_BOTTOM:
                    break;
                case INDEX_MAX_WIDTH:
                    break;
                case INDEX_MAX_HEIGHT:
                    break;
                case INDEX_MIN_WIDTH:
                    break;
                case INDEX_MIN_HEIGHT:
                    break;
                case INDEX_DRAWABLE_PADDING:
                    break;
            }
        }
        array.recycle();
    }

    private static boolean isPxVal(TypedValue val) {
        if (val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX) {
            return true;
        }
        return false;
    }

    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data >> TypedValue.COMPLEX_UNIT_SHIFT);
    }
}
