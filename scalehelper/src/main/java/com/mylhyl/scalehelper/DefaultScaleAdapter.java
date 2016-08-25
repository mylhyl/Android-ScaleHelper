package com.mylhyl.scalehelper;

import android.content.Context;
import android.util.Log;

/**
 * Created by hupei on 2016/3/8.
 */
class DefaultScaleAdapter implements ScaleAdapter{

    private static final String TAG = "DefaultScaleAdapter";
    private Context mContext;

    public DefaultScaleAdapter(Context context) {
        mContext = context;
        double devicePhysicalSize = ScaleHelper.getScreenSize(mContext);
        Log.d(TAG, "DefaultScaleAdapter: "+devicePhysicalSize);
    }

    @Override
    public float adapt(float scale, int screenWidth, int screenHeight) {
        if (screenWidth < 720 || screenHeight < 720) {//针对小屏（小分辨率）设备做调整
            if (screenWidth <= 480 || screenHeight <= 480) {//普通480设备
                scale *= 1.2f;
            } else {
                if (ScaleHelper.getScreenSize(mContext) < 4.0) {//小屏手机，较高分辨率（如 mx）
                    scale *= 1.3f;
                } else {//华为U9200
                    scale *= 1.05f;
                }
            }
        }
        return scale;
    }
}
