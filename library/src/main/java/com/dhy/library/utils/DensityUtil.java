package com.dhy.library.utils;

import android.content.Context;

/**
 * Created by dhy on 2017/12/19.
 */

public class DensityUtil {
    public DensityUtil() {

    }

    public static DensityUtil getInstance() {
        return DensityHolder.newInstance;
    }

    private static class DensityHolder {
        private final static DensityUtil newInstance = new DensityUtil();
    }

    public float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public int dp2px(Context context,float dip){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density +0.5f);
    }

    public int px2dip(Context context,float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density +0.5f);
    }

    public int px2sp (Context context,int px){
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaleDensity + 0.5f);
    }

    public int sp2px(Context context,int sp){
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaleDensity + 0.5f);
    }
}
