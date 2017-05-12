package com.hqyxjy.ldf.chartroidlib.intf;

import android.graphics.PointF;
import android.graphics.RectF;

import com.hqyxjy.ldf.chartroidlib.component.ChartData;
import com.hqyxjy.ldf.chartroidlib.component.ValueFormatter;

/**
 * Created by ldf on 17/5/12.
 */

public interface ChartInterface {
    /**
     * Returns the minimum x-value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getXChartMin();

    /**
     * Returns the maximum x-value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getXChartMax();

    /**
     * Returns the minimum y-value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getYChartMin();

    /**
     * Returns the maximum y-value of the chart, regardless of zoom or translation.
     *
     * @return
     */
    float getYChartMax();

    int getXValCount();

    int getWidth();

    int getHeight();

    PointF getCenterOfView();

    PointF getCenterOffsets();

    RectF getContentRect();

    ValueFormatter getDefaultValueFormatter();

    ChartData getData();
}
