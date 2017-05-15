package com.hqyxjy.ldf.chartroidlib.component;

/**
 * Created by ldf on 17/5/15.
 */

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

import com.hqyxjy.ldf.chartroidlib.base.ChartTrackHandler;

import java.util.List;

/**
 * Transformer class that contains all matrices and is responsible for
 * transforming values into pixels on the screen and backwards.
 *
 * @author Philipp Jahoda
 */
public class Transformer {

    /**
     * matrix to map the values to the screen pixels
     */
    protected Matrix mMatrixValueToPx = new Matrix();

    /**
     * matrix for handling the different offsets of the chart
     */
    protected Matrix mMatrixOffset = new Matrix();

    protected ChartTrackHandler chartTrackHandler;

    public Transformer(ChartTrackHandler chartTrackHandler) {
        this.chartTrackHandler = chartTrackHandler;
    }

    /**
     * Prepares the matrix that transforms values to pixels. Calculates the
     * scale factors from the charts size and offsets.
     *
     * @param xChartMin
     * @param deltaX
     * @param deltaY
     * @param yChartMin
     */
    public void prepareMatrixValuePx(float xChartMin, float deltaX, float deltaY, float yChartMin) {

        float scaleX = ((chartTrackHandler.contentWidth()) / deltaX);
        float scaleY = ((chartTrackHandler.contentHeight()) / deltaY);
        if (Float.isInfinite(scaleX))
        {
            scaleX = 0;
        }
        if (Float.isInfinite(scaleY))
        {
            scaleY = 0;
        }

        // setup all matrices
        mMatrixValueToPx.reset();
        mMatrixValueToPx.postTranslate(-xChartMin, -yChartMin);
        mMatrixValueToPx.postScale(scaleX, - scaleY);
    }

    /**
     * Prepares the matrix that contains all offsets.
     *
     * @param inverted
     */
    public void prepareMatrixOffset(boolean inverted) {

        mMatrixOffset.reset();

        // offset.postTranslate(mOffsetLeft, getHeight() - mOffsetBottom);

        if (!inverted)
            mMatrixOffset.postTranslate(chartTrackHandler.offsetLeft(),
                    chartTrackHandler.getChartHeight() - chartTrackHandler.offsetBottom());
        else {
            mMatrixOffset
                    .setTranslate(chartTrackHandler.offsetLeft(), -chartTrackHandler.offsetTop());
            mMatrixOffset.postScale(1.0f, -1.0f);
        }

        // mMatrixOffset.set(offset);

        // mMatrixOffset.reset();
        //
        // mMatrixOffset.postTranslate(mOffsetLeft, getHeight() -
        // mOffsetBottom);
    }

    /**
     * transform a path with all the given matrices VERY IMPORTANT: keep order
     * to value-touch-offset
     *
     * @param path
     */
    public void pathValueToPixel(Path path) {

        path.transform(mMatrixValueToPx);
        path.transform(chartTrackHandler.getTouchMatrix());
        path.transform(mMatrixOffset);
    }

    /**
     * Transforms multiple paths will all matrices.
     *
     * @param paths
     */
    public void pathValuesToPixel(List<Path> paths) {

        for (int i = 0; i < paths.size(); i++) {
            pathValueToPixel(paths.get(i));
        }
    }

    /**
     * Transform an array of points with all matrices. VERY IMPORTANT: Keep
     * matrix order "value-touch-offset" when transforming.
     *
     * @param pts
     */
    public void pointValuesToPixel(float[] pts) {

        mMatrixValueToPx.mapPoints(pts);
        chartTrackHandler.getTouchMatrix().mapPoints(pts);
        mMatrixOffset.mapPoints(pts);
    }

    /**
     * Transform a rectangle with all matrices.
     *
     * @param r
     */
    public void rectValueToPixel(RectF r) {

        mMatrixValueToPx.mapRect(r);
        chartTrackHandler.getTouchMatrix().mapRect(r);
        mMatrixOffset.mapRect(r);
    }

    /**
     * Transform a rectangle with all matrices with potential animation phases.
     *
     * @param r
     * @param phaseY
     */
    public void rectValueToPixel(RectF r, float phaseY) {

        // multiply the height of the rect with the phase
        r.top *= phaseY;
        r.bottom *= phaseY;

        mMatrixValueToPx.mapRect(r);
        chartTrackHandler.getTouchMatrix().mapRect(r);
        mMatrixOffset.mapRect(r);
    }

    /**
     * Transform a rectangle with all matrices with potential animation phases.
     *
     * @param r
     */
    public void rectValueToPixelHorizontal(RectF r) {

        mMatrixValueToPx.mapRect(r);
        chartTrackHandler.getTouchMatrix().mapRect(r);
        mMatrixOffset.mapRect(r);
    }

    /**
     * Transform a rectangle with all matrices with potential animation phases.
     *
     * @param r
     * @param phaseY
     */
    public void rectValueToPixelHorizontal(RectF r, float phaseY) {

        // multiply the height of the rect with the phase
        r.left *= phaseY;
        r.right *= phaseY;

        mMatrixValueToPx.mapRect(r);
        chartTrackHandler.getTouchMatrix().mapRect(r);
        mMatrixOffset.mapRect(r);
    }

    /**
     * transforms multiple rects with all matrices
     *
     * @param rects
     */
    public void rectValuesToPixel(List<RectF> rects) {

        Matrix m = getValueToPixelMatrix();

        for (int i = 0; i < rects.size(); i++)
            m.mapRect(rects.get(i));
    }

    /**
     * Transforms the given array of touch positions (pixels) (x, y, x, y, ...)
     * into values on the chart.
     *
     * @param pixels
     */
    public void pixelsToValue(float[] pixels) {

        Matrix tmp = new Matrix();

        // invert all matrixes to convert back to the original value
        mMatrixOffset.invert(tmp);
        tmp.mapPoints(pixels);

        chartTrackHandler.getTouchMatrix().invert(tmp);
        tmp.mapPoints(pixels);

        mMatrixValueToPx.invert(tmp);
        tmp.mapPoints(pixels);
    }

    /**
     * Returns the x and y values in the chart at the given touch point
     * (encapsulated in a PointD). This method transforms pixel coordinates to
     * coordinates / values in the chart. This is the opposite method to
     * getPixelsForValues(...).
     *
     * @param x
     * @param y
     * @return
     */
    public Point getValuesByTouchPoint(float x, float y) {

        // create an array of the touch-point
        float[] pts = new float[2];
        pts[0] = x;
        pts[1] = y;

        pixelsToValue(pts);

        double xTouchVal = pts[0];
        double yTouchVal = pts[1];

        return new Point(xTouchVal, yTouchVal);
    }

    public Matrix getValueMatrix() {
        return mMatrixValueToPx;
    }

    public Matrix getOffsetMatrix() {
        return mMatrixOffset;
    }

    private Matrix mMBuffer1 = new Matrix();

    public Matrix getValueToPixelMatrix() {
        mMBuffer1.set(mMatrixValueToPx);
        mMBuffer1.postConcat(chartTrackHandler.getTouchMatrix());
        mMBuffer1.postConcat(mMatrixOffset);
        return mMBuffer1;
    }

    private Matrix mMBuffer2 = new Matrix();

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(mMBuffer2);
        return mMBuffer2;
    }
}
