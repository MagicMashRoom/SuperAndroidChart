package com.hqyxjy.ldf.chartroidlib.base;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by ldf on 17/5/8.
 */

import com.hqyxjy.ldf.chartroidlib.component.Entry;
import com.hqyxjy.ldf.chartroidlib.data.ChartData;
import com.hqyxjy.ldf.chartroidlib.data.IDataSet;

public abstract class Chart<T extends ChartData<? extends IDataSet<? extends Entry>>> extends ViewGroup{
    /**
     * Extra offsets to be appended to the viewport
     */
    private float extraTopOffset = 0.f,
            extraRightOffset = 0.f,
            extraBottomOffset = 0.f,
            extraLeftOffset = 0.f;

    private float mMinTopOffset = 0.f,
            minRightOffset = 0.f,
            minBottomOffset = 0.f,
            minLeftOffset = 0.f;

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
