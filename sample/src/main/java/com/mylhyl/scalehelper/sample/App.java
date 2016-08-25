package com.mylhyl.scalehelper.sample;

import android.app.Application;

import com.mylhyl.scalehelper.ScaleConfig;

/**
 * Created by hupei on 2016/8/24.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ScaleConfig.init(this);
    }
}
