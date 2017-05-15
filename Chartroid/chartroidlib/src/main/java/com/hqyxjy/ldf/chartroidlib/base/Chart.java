package com.hqyxjy.ldf.chartroidlib.base;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by ldf on 17/5/8.
 */

public class Chart extends ViewGroup{
    /**
     * Extra offsets to be appended to the viewport
     */
    private float mExtraTopOffset = 0.f,
            mExtraRightOffset = 0.f,
            mExtraBottomOffset = 0.f,
            mExtraLeftOffset = 0.f;

     public Chart(Context context){
         super(context);
         init();
     }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private void init() {

    }
}
