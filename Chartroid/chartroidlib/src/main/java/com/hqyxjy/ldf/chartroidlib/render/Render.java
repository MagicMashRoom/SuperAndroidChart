package com.hqyxjy.ldf.chartroidlib.render;

import com.hqyxjy.ldf.chartroidlib.base.ChartTrackHandler;
import com.hqyxjy.ldf.chartroidlib.intf.BarLineScatterCandleBubbleDataProvider;

/**
 * Created by ldf on 17/5/6.
 */

public abstract class Render {
    private ChartTrackHandler chartTrackHandler;
    private int mMinX;
    private int mMaxX;

    public Render(ChartTrackHandler chartTrackHandler) {
        this.chartTrackHandler = chartTrackHandler;
    }
    /**
     * Returns true if the specified value fits in  between the provided min
     * and max bounds, false if not.
     *
     * @param val
     * @param min
     * @param max
     * @return
     */
    protected boolean fitsBounds(float val, float min, float max) {

        if (val < min || val > max)
            return false;
        else
            return true;
    }

    /**
     * Calculates the minimum and maximum x-value the chart can currently
     * display (with the given zoom level). -> mMinX, mMaxX
     *
     * @param dataProvider
     * @param xAxisModulus
     */
    public void calcXBounds(BarLineScatterCandleBubbleDataProvider dataProvider, int xAxisModulus) {

        int low = dataProvider.getLowestVisibleXIndex();
        int high = dataProvider.getHighestVisibleXIndex();

        int subLow = (low % xAxisModulus == 0) ? xAxisModulus : 0;

        mMinX = Math.max((low / xAxisModulus) * (xAxisModulus) - subLow, 0);
        mMaxX = Math.min((high / xAxisModulus) * (xAxisModulus) + xAxisModulus, (int) dataProvider.getXChartMax());
    }
}
