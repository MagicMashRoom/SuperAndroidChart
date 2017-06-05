package com.hqyxjy.ldf.chartroidlib.base;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by ldf on 17/5/8.
 */

public class ChartTrackHandler {

    /**
     * RectF used for draw chart
     */
    private RectF content;
    private float offsetLeft;
    private float offsetTop;
    private float offsetRight;
    private float offsetBottom;

    /**
     * transMatrix used for transfrom events
     */
    protected final Matrix transMatrix = new Matrix();
    /**
     * touchMatrix used for touch events
     */
    protected final Matrix touchMatrix = new Matrix();

    private float chartWidth;
    private float chartHeight;

    public void setTrackSize(float width , float height ) {
        float offsetLeft = this.offsetLeft();
        float offsetTop = this.offsetTop();
        float offsetRight = this.offsetRight();
        float offsetBottom = this.offsetBottom();
        chartHeight = height;
        chartWidth = width;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    private void restrainViewPort(float offsetLeft, float offsetTop,
                                  float offsetRight, float offsetBottom) {
        content.set(offsetLeft, offsetTop, chartWidth - offsetRight, chartHeight
                - offsetBottom);
    }


    public float offsetLeft() {
        return content.left;
    }

    public float offsetRight() {
        return chartWidth - content.right;
    }

    public float offsetTop() {
        return content.top;
    }

    public float offsetBottom() {
        return chartHeight - content.bottom;
    }

    public float contentTop() {
        return content.top;
    }

    public float contentLeft() {
        return content.left;
    }

    public float contentRight() {
        return content.right;
    }

    public float contentBottom() {
        return content.bottom;
    }

    public float contentWidth() {
        return content.width();
    }

    public float contentHeight() {
        return content.height();
    }

    public RectF getContentRectF() {
        return content;
    }

    public void setContentRectF(RectF content) {
        this.content = content;
    }

    private float mScaleX;
    private float mScaleY;
    private float mTransX;
    private float mTransY;
    private float minScaleX = 1f;
    private float maxScaleX = Float.MAX_VALUE;
    private float minScaleY = 1f;
    private float maxScaleY = Float.MAX_VALUE;
    private float transOffsetX;
    private float transOffsetY;
    private float[] matrixArray = new float[9];

    /**
     * call this method to refresh the graph with a given matrix
     *
     * @param newMatrix
     * @return
     */
    public Matrix refresh(final Matrix newMatrix, View chart, boolean invalidate) {

        transMatrix.set(newMatrix);

        // make sure scale and translation are within their bounds
        limitTransAndScale(newMatrix , content);

        if (invalidate)
            chart.invalidate();

        newMatrix.set(transMatrix);
        return newMatrix;
    }

    private void limitTransAndScale(Matrix newMatrix, RectF content) {
        newMatrix.getValues(matrixArray);

        float curTransX = matrixArray[Matrix.MTRANS_X];
        float curScaleX = matrixArray[Matrix.MSCALE_X];

        float curTransY = matrixArray[Matrix.MTRANS_Y];
        float curScaleY = matrixArray[Matrix.MSCALE_Y];

        // min scale-x is 1f
        mScaleX = Math.min(Math.max(minScaleX, curScaleX), maxScaleX);

        // min scale-y is 1f
        mScaleY = Math.min(Math.max(minScaleY, curScaleY), maxScaleY);

        float width = 0f;
        float height = 0f;

        if (this.content != null) {
            width = this.content.width();
            height = this.content.height();
        }

        float maxTransX = -width * (mScaleX - 1f);
        mTransX = Math.min(Math.max(curTransX, maxTransX - transOffsetX), transOffsetX);

        float maxTransY = height * (mScaleY - 1f);
        mTransY = Math.max(Math.min(curTransY, maxTransY + transOffsetY), -transOffsetY);

        matrixArray[Matrix.MTRANS_X] = mTransX;
        matrixArray[Matrix.MSCALE_X] = mScaleX;

        matrixArray[Matrix.MTRANS_Y] = mTransY;
        matrixArray[Matrix.MSCALE_Y] = mScaleY;

        newMatrix.setValues(matrixArray);
    }

    /**
     * call this method to zoom the graph
     * @param1  scaleX
     * @param2  scaleY
     * @return
     */
    public Matrix zoom(float scaleX , float scaleY) {

        Matrix save = new Matrix();
        save.set(transMatrix);

        save.postScale(scaleX, scaleY);

        return save;
    }

    /**
     * call this method to zoom the graph
     * @param1  scaleX
     * @param2  scaleY
     * @return
     */
    public Matrix translate(final float[] aimPoint) {

        Matrix save = new Matrix();
        save.set(transMatrix);
        float transX = aimPoint[0] - offsetLeft();
        float transY = aimPoint[1] - offsetTop();
        save.postTranslate(transX, transY);

        return save;
    }

    public float getChartHeight() {
        return 0;
    }

    public Matrix getTouchMatrix() {
        return touchMatrix;
    }

    public Matrix getTransMatrix() {
        return transMatrix;
    }
}
