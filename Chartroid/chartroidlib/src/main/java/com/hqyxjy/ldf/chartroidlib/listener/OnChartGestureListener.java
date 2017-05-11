package com.hqyxjy.ldf.chartroidlib.listener;

import android.view.MotionEvent;

/**
 * Created by ldf on 17/5/6.
 */

public interface OnChartGestureListener {
    /**
     * Callbacks when a touch-gesture has started on the Chart (ACTION_DOWN)
     *
     * @param me
     * @param lastPerformedGesture
     */
    void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture);

    /**
     * Callbacks when a touch-gesture has ended on the Chart (ACTION_UP, ACTION_CANCEL)
     *
     * @param me
     * @param lastPerformedGesture
     */
    void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture);

    /**
     * Callbacks when the Chart is longpressed.
     *
     * @param me
     */
    void onChartLongPressed(MotionEvent me);

    /**
     * Callbacks when the Chart is double-tapped.
     *
     * @param me
     */
    void onChartDoubleTapped(MotionEvent me);

    /**
     * Callbacks when the Chart is single-tapped.
     *
     * @param me
     */
    void onChartSingleTapped(MotionEvent me);

    /**
     * Callbacks then a fling gesture is made on the Chart.
     *
     * @param me1
     * @param me2
     * @param velocityX
     * @param velocityY
     */
    void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY);

    /**
     * Callbacks when the Chart is scaled / zoomed via pinch zoom gesture.
     *
     * @param me
     * @param scaleX scalefactor on the x-axis
     * @param scaleY scalefactor on the y-axis
     */
    void onChartScale(MotionEvent me, float scaleX, float scaleY);

    /**
     * Callbacks when the Chart is moved / translated via drag gesture.
     *
     * @param me
     * @param dX translation distance on the x-axis
     * @param dY translation distance on the y-axis
     */
    void onChartTranslate(MotionEvent me, float dX, float dY);
}
