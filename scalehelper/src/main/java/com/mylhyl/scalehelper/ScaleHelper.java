package com.mylhyl.scalehelper;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by hupei on 2016/3/8.
 */
public class ScaleHelper {


    /**
     * @param context
     * @param attrs
     */
    public static void scaleView(Context context, AttributeSet attrs) {

    }

    /**
     * 将 view 的 width、height、Padding、Margin 属性百分比化
     *
     * @param view
     */
    public static void scaleView(View view) {
        scaleSize(view);
        scalePadding(view);
        scaleMargin(view);
    }

    /**
     * 将 view LayoutParams 的 width、height 百分比化
     *
     * @param view
     */
    public static void scaleSize(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null) return;

        if (lp.width > 0) {
            lp.width = scaleValue(lp.width);
        }

        if (lp.height > 0) {
            lp.height = scaleValue(lp.height);
        }
        view.setLayoutParams(lp);
    }

    /**
     * 将view Padding 的 left、top、right、bottom 百分比化
     *
     * @param view
     */
    public static void scalePadding(View view) {
        int left = view.getPaddingLeft();
        int top = view.getPaddingTop();
        int right = view.getPaddingRight();
        int bottom = view.getPaddingBottom();

        left = scaleValue(left);
        top = scaleValue(top);
        right = scaleValue(right);
        bottom = scaleValue(bottom);

        view.setPadding(left, top, right, bottom);
    }

    public static void scalePadding(View v, int valPx) {
        scalePadding(v, valPx, valPx, valPx, valPx);
    }

    public static void scalePaddingLeft(View v, int valPx) {
        scalePadding(v, valPx, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
    }

    public static void scalePaddingTop(View v, int valPx) {
        scalePadding(v, v.getPaddingLeft(), valPx, v.getPaddingRight(), v.getPaddingBottom());
    }

    public static void scalePaddingRight(View v, int valPx) {
        scalePadding(v, v.getPaddingLeft(), v.getPaddingTop(), valPx, v.getPaddingBottom());
    }

    public static void scalePaddingBottom(View v, int valPx) {
        scalePadding(v, v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), valPx);
    }

    public static void scalePadding(View v, int l, int t, int r, int b) {
        int left = scaleValue(l);
        int top = scaleValue(t);
        int right = scaleValue(r);
        int bottom = scaleValue(b);
        v.setPadding(left, top, right, bottom);
    }

    /**
     * 将view Margin 的 left、top、right、bottom 百分比化
     *
     * @param view
     */
    public static void scaleMargin(View view) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp == null) return;

        lp.leftMargin = scaleValue(lp.leftMargin);
        lp.topMargin = scaleValue(lp.topMargin);
        lp.rightMargin = scaleValue(lp.rightMargin);
        lp.bottomMargin = scaleValue(lp.bottomMargin);
        view.setLayoutParams(lp);
    }

    public static void scaleTextSize(TextView textView, int valPx) {
        textView.setIncludeFontPadding(false);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, scaleValue(valPx));
    }

    /**
     * 将像素值百分比化
     *
     * @param pxVal
     * @return
     */
    public static int scaleValue(int pxVal) {
        int val = (int) (pxVal * getScale());
        if (val == 0 && pxVal > 0) {//for very thin divider
            val = 1;
        }
        return val;
    }

    /**
     * 获取屏幕尺寸，实际物理尺寸<br>
     * 三角形的勾股定理：对角线的长度=长与宽的平方和再开方
     *
     * @param context
     * @return
     */
    public static double getScreenSize(Context context) {
        //宽、高
        int[] size = getResolutionSize(context);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        //宽、高的平方
        double x = Math.pow(size[0] / dm.xdpi, 2);
        double y = Math.pow(size[1] / dm.ydpi, 2);
        //宽、高和开方
        return Math.sqrt(x + y);
    }

    /**
     * 获取屏幕分辨率，屏幕纵、横方向像素个数
     *
     * @param context
     * @return
     */
    public static int[] getResolutionSize(Context context) {
        int[] size = new int[2];

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                size[0] = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size[1] = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception ignored) {
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        else if (Build.VERSION.SDK_INT >= 17) {
            Point realSize = new Point();
            display.getRealSize(realSize);
            size[0] = realSize.x;
            size[1] = realSize.y;
        }
        // since SDK_INT = 1;
        if (size[0] == 0 || size[1] == 0) {
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            size[0] = metrics.widthPixels;
            size[1] = metrics.heightPixels;
        }

        return size;
    }

    public static int getScreenWidth() {
        return ScaleConfig.getInstance().getScreenWidth();
    }

    public static int getScreenHeight() {
        return ScaleConfig.getInstance().getScreenHeight();
    }

    public static float getScale() {
        return ScaleConfig.getInstance().getScale();
    }
}
