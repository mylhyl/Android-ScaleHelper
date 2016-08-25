package com.mylhyl.scalehelper;

import android.content.Context;

/**
 * Created by hupei on 2016/3/8.
 */
public class ScaleConfig {
    private static ScaleConfig sInstance;

    private int mScreenWidth;
    private int mScreenHeight;

    private int mDesignWidth;
    private int mDesignHeight;

    private float mScale;

    private ScaleConfig(int designWidth, int designHeight) {
        this.mDesignWidth = designWidth;
        this.mDesignHeight = designHeight;
    }

    /**
     * 初始化<br>
     * 设计稿默认参考尺寸 designWidth = 1080；designHeight = 1920
     *
     * @param context
     */
    public static void init(Context context) {
        init(context, 1080, 1920);
    }

    /**
     * 初始化
     *
     * @param context
     * @param designWidth  设计稿尺寸宽
     * @param designHeight 设计稿尺寸高
     */
    public static void init(Context context, int designWidth, int designHeight) {
        if (sInstance == null) {
            sInstance = new ScaleConfig(designWidth, designHeight);
            Context appContext = context.getApplicationContext();
            sInstance.initInternal(appContext, new DefaultScaleAdapter(appContext));
        }
    }

    public static ScaleConfig getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Must init before using.");
        }
        return sInstance;
    }

    private void initInternal(Context context, ScaleAdapter scaleAdapter) {
        checkParams();
        int[] size = ScaleHelper.getResolutionSize(context);
        mScreenWidth = size[0];
        mScreenHeight = size[1];

        //横屏状态下，宽高互换，按竖屏模式计算scale
        if (mScreenWidth > mScreenHeight) {
            mScreenWidth = mScreenWidth + mScreenHeight;
            mScreenHeight = mScreenWidth - mScreenHeight;
            mScreenWidth = mScreenWidth - mScreenHeight;
        }

        float deviceScale = (float) mScreenHeight / mScreenWidth;
        float designScale = (float) mDesignHeight / mDesignWidth;
        //高宽比小于等于标准比（较标准屏宽一些），以高为基准计算scale（以短边计算），否则以宽为基准计算scale
        if (deviceScale <= designScale) {
            mScale = (float) mScreenHeight / mDesignHeight;
        } else {
            mScale = (float) mScreenWidth / mDesignWidth;
        }

        if (scaleAdapter != null) {
            mScale = scaleAdapter.adapt(mScale, mScreenWidth, mScreenHeight);
        }
    }

    private void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException("must mDesignHeight and mDesignWidth > 0");
        }
    }

    int getScreenWidth() {
        return mScreenWidth;
    }

    int getScreenHeight() {
        return mScreenHeight;
    }

    float getScale() {
        return mScale;
    }
}
