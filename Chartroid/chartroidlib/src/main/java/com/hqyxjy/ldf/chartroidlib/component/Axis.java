package com.hqyxjy.ldf.chartroidlib.component;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;

import com.hqyxjy.ldf.chartroidlib.utils.UserInterfaceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldf on 17/5/6.
 */

public abstract class Axis extends Component{
    private int gridColor = Color.GRAY;

    private float gridLineWidth = 1f;

    private int axisColor = Color.GRAY;

    private float axisWidth = 1f;

    protected boolean isDrawGridLine = true;

    protected boolean isDrawAxisLine = true;

    protected boolean isDrawAxisLabels = true;

    /**
     * 绘画虚线的DashPathEffect
     */
    private DashPathEffect gridDashPathEffect = null;

    /**
     * array of limit lines that can be set for the axis
     */
    protected List<LimitLine> mLimitLines;

    /**
     * flag indicating the limit lines layer depth
     */
    protected boolean isDrawLimitLineBehindData = false;

    /**
     * flag indicating that the axis-min value has been customized
     */
    protected boolean isCustomerSetAxisMin = false;

    /**
     * flag indicating that the axis-max value has been customized
     */
    protected boolean isCustomerSetAxisMax = false;

    /**
     * don't touch this direclty, use setter
     */
    private float axisMaximum = 0f;

    /**
     * don't touch this directly, use setter
     */
    private float axisMinimum = 0f;

    /**
     * the total range of values this axis covers
     */
    public float axisRange = 0f;

    /**
     * default constructor
     */
    public Axis() {
        this.textSize = UserInterfaceUtils.dp2px(10f);
        this.XOffset = UserInterfaceUtils.dp2px(5f);
        this.YOffset = UserInterfaceUtils.dp2px(5f);
        this.mLimitLines = new ArrayList<LimitLine>();
    }

    /**
     * Set this to true to enable drawing the grid lines for this axis.
     *
     * @param enabled
     */
    public void setDrawGridLines(boolean enabled) {
        isDrawGridLine = enabled;
    }

    /**
     * Returns true if drawing grid lines is enabled for this axis.
     *
     * @return
     */
    public boolean isDrawGridLinesEnabled() {
        return isDrawGridLine;
    }

    /**
     * Set this to true if the line alongside the axis should be drawn or not.
     *
     * @param enabled
     */
    public void setDrawAxisLine(boolean enabled) {
        isDrawAxisLine = enabled;
    }

    /**
     * Returns true if the line alongside the axis should be drawn.
     *
     * @return
     */
    public boolean isDrawAxisLineEnabled() {
        return isDrawAxisLine;
    }

    /**
     * Sets the color of the grid lines for this axis (the horizontal lines
     * coming from each label).
     *
     * @param color
     */
    public void setGridColor(int color) {
        gridColor = color;
    }

    /**
     * Returns the color of the grid lines for this axis (the horizontal lines
     * coming from each label).
     *
     * @return
     */
    public int getGridColor() {
        return gridColor;
    }

    /**
     * Sets the width of the border surrounding the chart in dp.
     *
     * @param width
     */
    public void setAxisLineWidth(float width) {
        axisWidth = UserInterfaceUtils.dp2px(width);
    }

    /**
     * Returns the width of the axis line (line alongside the axis).
     *
     * @return
     */
    public float getAxisLineWidth() {
        return axisWidth;
    }

    /**
     * Sets the width of the grid lines that are drawn away from each axis
     * label.
     *
     * @param width
     */
    public void setGridLineWidth(float width) {
        gridLineWidth = UserInterfaceUtils.dp2px(width);
    }

    /**
     * Returns the width of the grid lines that are drawn away from each axis
     * label.
     *
     * @return
     */
    public float getGridLineWidth() {
        return gridLineWidth;
    }

    /**
     * Sets the color of the border surrounding the chart.
     *
     * @param color
     */
    public void setAxisLineColor(int color) {
        axisColor = color;
    }

    /**
     * Returns the color of the axis line (line alongside the axis).
     *
     * @return
     */
    public int getAxisLineColor() {
        return axisColor;
    }

    /**
     * Set this to true to enable drawing the labels of this axis (this will not
     * affect drawing the grid lines or axis lines).
     *
     * @param enabled
     */
    public void setDrawLabels(boolean enabled) {
        isDrawAxisLabels = enabled;
    }

    /**
     * Returns true if drawing the labels is enabled for this axis.
     *
     * @return
     */
    public boolean isDrawLabelsEnabled() {
        return isDrawAxisLabels;
    }

    /**
     * Adds a new LimitLine to this axis.
     *
     * @param l
     */
    public void addLimitLine(LimitLine l) {
        mLimitLines.add(l);

        if (mLimitLines.size() > 6) {
            Log.e("MPAndroiChart",
                    "Warning! You have more than 6 LimitLines on your axis, do you really want " +
                            "that?");
        }
    }

    /**
     * Removes the specified LimitLine from the axis.
     *
     * @param l
     */
    public void removeLimitLine(LimitLine l) {
        mLimitLines.remove(l);
    }

    /**
     * Removes all LimitLines from the axis.
     */
    public void removeAllLimitLines() {
        mLimitLines.clear();
    }

    /**
     * Returns the LimitLines of this axis.
     *
     * @return
     */
    public List<LimitLine> getLimitLines() {
        return mLimitLines;
    }

    /**
     * If this is set to true, the LimitLines are drawn behind the actual data,
     * otherwise on top. Default: false
     *
     * @param enabled
     */
    public void setDrawLimitLinesBehindData(boolean enabled) {
        isDrawLimitLineBehindData = enabled;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return isDrawLimitLineBehindData;
    }

    /**
     * Returns the longest formatted label (in terms of characters), this axis
     * contains.
     *
     * @return
     */
    public abstract String getLongestLabel();

    /**
     * Enables the grid line to be drawn in dashed mode, e.g. like this
     * "- - - - - -". THIS ONLY WORKS IF HARDWARE-ACCELERATION IS TURNED OFF.
     * Keep in mind that hardware acceleration boosts performance.
     *
     * @param lineLength  the length of the line pieces
     * @param spaceLength the length of space in between the pieces
     * @param phase       offset, in degrees (normally, use 0)
     */
    public void enableGridDashedLine(float lineLength, float spaceLength, float phase) {
        gridDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Disables the grid line to be drawn in dashed mode.
     */
    public void disableGridDashedLine() {
        gridDashPathEffect = null;
    }

    /**
     * Returns true if the grid dashed-line effect is enabled, false if not.
     *
     * @return
     */
    public boolean isGridDashedLineEnabled() {
        return gridDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for grid line
     *
     * @return
     */
    public DashPathEffect getGridDashPathEffect() {
        return gridDashPathEffect;
    }

    /**
     * ###### BELOW CODE RELATED TO CUSTOM AXIS VALUES ######
     */

    public float getAxisMaximum() {
        return axisMaximum;
    }

    public float getAxisMinimum() {
        return axisMinimum;
    }

    /**
     * By calling this method, any custom maximum value that has been previously set is reseted,
     * and the calculation is
     * done automatically.
     */
    public void resetAxisMaxValue() {
        isCustomerSetAxisMax = false;
    }

    /**
     * Returns true if the axis max value has been customized (and is not calculated automatically)
     *
     * @return
     */
    public boolean isAxisMaxCustom() {
        return isCustomerSetAxisMax;
    }

    /**
     * By calling this method, any custom minimum value that has been previously set is reseted,
     * and the calculation is
     * done automatically.
     */
    public void resetAxisMinValue() {
        isCustomerSetAxisMin = false;
    }

    /**
     * Returns true if the axis min value has been customized (and is not calculated automatically)
     *
     * @return
     */
    public boolean isAxisMinCustom() {
        return isCustomerSetAxisMin;
    }

    /**
     * Set a custom minimum value for this axis. If set, this value will not be calculated
     * automatically depending on
     * the provided data. Use resetAxisMinValue() to undo this. Do not forget to call
     * setStartAtZero(false) if you use
     * this method. Otherwise, the axis-minimum value will still be forced to 0.
     *
     * @param min
     */
    public void setAxisMinValue(float min) {
        isCustomerSetAxisMin = true;
        axisMinimum = min;
    }

    /**
     * Set a custom maximum value for this axis. If set, this value will not be calculated
     * automatically depending on
     * the provided data. Use resetAxisMaxValue() to undo this.
     *
     * @param max
     */
    public void setAxisMaxValue(float max) {
        isCustomerSetAxisMax = true;
        axisMaximum = max;
    }
}
