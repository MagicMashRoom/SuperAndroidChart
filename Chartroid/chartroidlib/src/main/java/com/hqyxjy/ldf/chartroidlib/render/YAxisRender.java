package com.hqyxjy.ldf.chartroidlib.render;

import android.graphics.Canvas;

import com.hqyxjy.ldf.chartroidlib.base.ChartTrackHandler;

import javax.xml.transform.Transformer;

/**
 * Created by ldf on 17/5/9.
 */

public class YAxisRender extends AxisRender{

    public YAxisRender(ChartTrackHandler chartTrackHandler, Transformer trans) {
        super(chartTrackHandler, trans);
    }

    @Override
    public void renderAxisLabels(Canvas c) {

    }

    @Override
    public void renderGridLines(Canvas c) {

    }

    @Override
    public void renderAxisLine(Canvas c) {

    }

    @Override
    public void renderLimitLines(Canvas c) {

    }
}
