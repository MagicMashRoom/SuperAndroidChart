package com.hqyxjy.ldf.chartroidlib.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewConfiguration;

/**
 * Created by ldf on 17/5/9.
 */

public class UserInterfaceUtils {

    private static DisplayMetrics mMetrics;
    private static int mMinimumFlingVelocity = 50;
    private static int mMaximumFlingVelocity = 8000;
    public final static double DEG2RAD = (Math.PI / 180.0);
    public final static float FDEG2RAD = ((float) Math.PI / 180.f);

    /**
     * initialize method, called inside the Chart.init() method.
     *
     * @param context
     */
    @SuppressWarnings("deprecation")
    public static void init(Context context) {

        if (context == null) {
            // noinspection deprecation
            mMinimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            // noinspection deprecation
            mMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();

            Log.e("MPChartLib-Utils"
                    , "Utils.init(...) PROVIDED CONTEXT OBJECT IS NULL");

        } else {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();

            Resources res = context.getResources();
            mMetrics = res.getDisplayMetrics();
        }
    }

    public static float dp2px(float dp) {
        if (mMetrics == null) {

            Log.e("MPChartLib-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.dp2px(...). Otherwise conversion does not " +
                            "take place.");
            return dp;
            // throw new IllegalStateException(
            // "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before
            // calling Utils.dp2px(...).");
        }

        DisplayMetrics metrics = mMetrics;
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float px2dp(float px) {

        if (mMetrics == null) {

            Log.e("MPChartLib-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.px2dp(...). Otherwise conversion does not" +
                            " take place.");
            return px;
            // throw new IllegalStateException(
            // "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before
            // calling Utils.px2dp(...).");
        }

        DisplayMetrics metrics = mMetrics;
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static int calcTextWidth(Paint paint, String measuredText) {
        return (int) paint.measureText(measuredText);
    }

    public static int calcTextHeight(Paint paint, String measuredText) {
        Rect r = new Rect();
        paint.getTextBounds(measuredText, 0, measuredText.length(), r);
        return r.height();
    }

    /**
     * Math.pow(...) is very expensive, so avoid calling it and create it
     * yourself.
     */
    private static final int POW_10[] = {
            1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000
    };

    private static final int POW_2[] = {
            1, 2, 4, 8, 16, 32, 64, 128, 512, 1024
    };
}
