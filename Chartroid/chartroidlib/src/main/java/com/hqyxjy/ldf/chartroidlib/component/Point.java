package com.hqyxjy.ldf.chartroidlib.component;

/**
 * Created by ldf on 17/5/15.
 */

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns a string representation of the object
     */
    public String toString() {
        return "PointD, x: " + x + ", y: " + y;
    }
}
