package com.hqyxjy.ldf.chartroidlib.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.hqyxjy.ldf.chartroidlib.base.ChartTrackHandler;

import javax.xml.transform.Transformer;

/**
 * Created by ldf on 17/5/9.
 */

public abstract class AxisRender extends Render{
    protected Transformer mTrans;

    /** paint object for the grid lines */
    protected Paint gridPaint;

    /** paint for the x-label values */
    protected Paint labelPaint;

    /** paint for the line surrounding the chart */
    protected Paint axisPaint;

    /** paint used for the limit lines */
    protected Paint limitLinePaint;

    public AxisRender(ChartTrackHandler chartTrackHandler, Transformer trans) {

        super(chartTrackHandler);

        this.mTrans = trans;

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        gridPaint = new Paint();
        gridPaint.setColor(Color.GRAY);
        gridPaint.setStrokeWidth(1f);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAlpha(90);

        axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(1f);
        axisPaint.setStyle(Paint.Style.STROKE);

        limitLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        limitLinePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Returns the Paint object used for drawing the axis (labels).
     *
     * @return
     */
    public Paint getPaintAxisLabels() {
        return labelPaint;
    }

    /**
     * Returns the Paint object that is used for drawing the grid-lines of the
     * axis.
     *
     * @return
     */
    public Paint getPaintGrid() {
        return gridPaint;
    }

    /**
     * Returns the Paint object that is used for drawing the axis-line that goes
     * alongside the axis.
     *
     * @return
     */
    public Paint getPaintAxisLine() {
        return axisPaint;
    }

    /**
     * Returns the Transformer object used for transforming the axis values.
     *
     * @return
     */
    public Transformer getTransformer() {
        return mTrans;
    }

    /**
     * Draws the axis labels to the screen.
     *
     * @param c
     */
    public abstract void renderAxisLabels(Canvas c);

    /**
     * Draws the grid lines belonging to the axis.
     *
     * @param c
     */
    public abstract void renderGridLines(Canvas c);

    /**
     * Draws the line that goes alongside the axis.
     *
     * @param c
     */
    public abstract void renderAxisLine(Canvas c);

    /**
     * Draws the LimitLines associated with this axis to the screen.
     *
     * @param c
     */
    public abstract void renderLimitLines(Canvas c);
}
