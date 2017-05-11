package com.hqyxjy.ldf.chartroidlib.render;

import com.hqyxjy.ldf.chartroidlib.base.ChartTrackHandler;

/**
 * Created by ldf on 17/5/6.
 */

public abstract class Render {
    private ChartTrackHandler chartTrackHandler;
    protected abstract void drawValues();
    protected abstract void drawExtras();
    protected abstract void drawHighLighted();
    protected abstract void drawData();
}
