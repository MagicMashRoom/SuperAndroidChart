package com.hqyxjy.ldf.chartroidlib.component;

/**
 * Created by ldf on 17/5/9.
 */

import android.graphics.Color;
import android.graphics.Typeface;

import com.hqyxjy.ldf.chartroidlib.utils.UserInterfaceUtils;

/**
 * This class encapsulates everything both Axis, Legend and LimitLines have in common.
 *
 * @author Philipp Jahoda
 */
public abstract class Component {

    /**
     * flag that indicates if this axis / legend is enabled or not
     */
    protected boolean enabled = true;

    /**
     * the offset in pixels this axis labels have on the x-axis
     */
    protected float XOffset = 5f;

    /**
     * the offset in pixels this axis labels have on the Y-axis
     */
    protected float YOffset = 5f;

    /**
     * the typeface used for the labels
     */
    protected Typeface typeface = null;

    /**
     * the text size of the labels
     */
    protected float textSize = 10f;

    /**
     * the text color to use for the labels
     */
    protected int textColor = Color.BLACK;

    public Component() {

    }

    /**
     * Returns the used offset on the x-axis for drawing the axis or legend
     * labels. This offset is applied before and after the label.
     *
     * @return
     */
    public float getXOffset() {
        return XOffset;
    }

    /**
     * Sets the used x-axis offset for the labels on this axis.
     *
     * @param xOffset
     */
    public void setXOffset(float xOffset) {
        this.XOffset = UserInterfaceUtils.dp2px(xOffset);
    }

    /**
     * Returns the used offset on the x-axis for drawing the axis labels. This
     * offset is applied before and after the label.
     *
     * @return
     */
    public float getYOffset() {
        return YOffset;
    }

    /**
     * Sets the used y-axis offset for the labels on this axis. For the legend,
     * higher offset means the legend as a whole will be placed further away
     * from the top.
     *
     * @param yOffset
     */
    public void setYOffset(float yOffset) {
        YOffset = UserInterfaceUtils.dp2px(yOffset);
    }

    /**
     * returns the Typeface used for the labels, returns null if none is set
     *
     * @return
     */
    public Typeface getTypeface() {
        return typeface;
    }

    /**
     * sets a specific Typeface for the labels
     *
     * @param tf
     */
    public void setTypeface(Typeface tf) {
        typeface = tf;
    }

    /**
     * sets the size of the label text in pixels min = 6f, max = 24f, default
     * 10f
     *
     * @param size
     */
    public void setTextSize(float size) {

        if (size > 24f)
            size = 24f;
        if (size < 6f)
            size = 6f;

        textSize = UserInterfaceUtils.dp2px(size);
    }

    /**
     * returns the text size that is currently set for the labels
     *
     * @return
     */
    public float getTextSize() {
        return textSize;
    }

    /**
     * Sets the text color to use for the labels. Make sure to use
     * getResources().getColor(...) when using a color from the resources.
     *
     * @param color
     */
    public void setTextColor(int color) {
        textColor = color;
    }

    /**
     * Returns the text color that is set for the labels.
     *
     * @return
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * Set this to true if this component should be enabled (should be drawn),
     * false if not. If disabled, nothing of this component will be drawn.
     * Default: true
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns true if this comonent is enabled (should be drawn), false if not.
     *
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }
}
