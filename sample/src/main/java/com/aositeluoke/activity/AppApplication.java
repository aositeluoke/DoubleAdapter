package com.aositeluoke.activity;

import android.app.Application;

import com.aositeluoke.library.image.GlideLoader;
import com.aositeluoke.library.image.ImageLoaderUtil;

/**
 * 类描述:
 * 作者:xues
 * 时间:2017年02月12日
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化图片加载库
        ImageLoaderUtil.init(this, GlideLoader.class);
    }
}
