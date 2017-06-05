package com.hqyxjy.ldf.chartroidlib.data.dataset;

import com.hqyxjy.ldf.chartroidlib.component.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldf on 17/5/17.
 */

public abstract class DataSet<T extends Entry> extends BaseDataSet<T> {

    /**
     * the entries that this dataset represents / holds together
     */
    protected List<T> vals = null;

    /**
     * maximum y-value in the y-value array
     */
    protected float yMax = 0.0f;

    /**
     * the minimum y-value in the y-value array
     */
    protected float yMin = 0.0f;

    /**
     * maximum y-value in the y-value array
     */
    protected float xMax = 0.0f;

    /**
     * the minimum y-value in the y-value array
     */
    protected float xMin = 0.0f;

    /**
     * Creates a new DataSet object with the given values it represents. Also, a
     * label that describes the DataSet can be specified. The label can also be
     * used to retrieve the DataSet from a ChartData object.
     *
     * @param yVals
     * @param label
     */
    public DataSet(List<T> yVals, String label) {
        super(label);
        this.vals = yVals;

        if (vals == null)
            vals = new ArrayList<T>();

        calcYMinMax(0, vals.size());
    }

    @Override
    public void calcMinMax() {
        if (vals == null || vals.isEmpty())
            return;

        yMax = -Float.MAX_VALUE;
        yMin = Float.MAX_VALUE;
        xMax = -Float.MAX_VALUE;
        xMin = Float.MAX_VALUE;

        for (T e : vals) {
            calcMinMax(e);
        }
    }

    protected void calcMinMax(T e) {
        if (e == null)
            return;

        calcMinMaxX(e);

        calcMinMaxY(e);
    }

    private void calcMinMaxY(T e) {
        if (e.getXVal() < xMin)
            xMin = e.getXVal();

        if (e.getXVal() > xMax)
            xMax = e.getXVal();
    }

    private void calcMinMaxX(T e) {
        if (e.getYVal() < yMin)
            yMin = e.getYVal();

        if (e.getYVal() > yMax)
            yMax = e.getYVal();
    }

    @Override
    public void calcYMinMax(int start, int end) {

        if (vals == null || vals.isEmpty())
            return;

        yMax = -Float.MAX_VALUE;
        yMin = Float.MAX_VALUE;

        int indexFrom = getEntryIndex(start, Rounding.DOWN);
        int indexTo = getEntryIndex(end, Rounding.UP);

        for (int i = indexFrom; i <= indexTo; i++) {

            // only recalculate y
            calcMinMaxY(vals.get(i));
        }
    }

    @Override
    public int getEntryCount() {
        return vals.size();
    }

    /**
     * Returns the array of y-values that this DataSet represents.
     *
     * @return
     */
    public List<T> getYVals() {
        return vals;
    }

    /**
     * Sets the array of y-values that this DataSet represents, and calls notifyDataSetChanged()
     *
     * @return
     */
    public void setYVals(List<T> yVals) {
        vals = yVals;
        notifyDataSetChanged();
    }

    /**
     * Provides an exact copy of the DataSet this method is used on.
     *
     * @return
     */
    public abstract DataSet<T> copy();

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(toSimpleString());
        for (int i = 0; i < vals.size(); i++) {
            buffer.append(vals.get(i).toString() + " ");
        }
        return buffer.toString();
    }

    /**
     * Returns a simple string representation of the DataSet with the type and
     * the number of Entries.
     *
     * @return
     */
    public String toSimpleString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("DataSet, label: " + (getLabel() == null ? "" : getLabel()) + ", entries: " + vals.size() + "\n");
        return buffer.toString();
    }

    @Override
    public float getYMinVal() {
        return yMin;
    }

    @Override
    public float getYMaxVal() {
        return yMax;
    }

    @Override
    public void clear() {
        vals.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean addEntry(T e) {

        if (e == null)
            return false;

        float val = e.getYVal();

        List<T> yVals = getYVals();
        if (yVals == null) {
            yVals = new ArrayList<T>();
        }

        if (yVals.size() == 0) {
            yMax = val;
            yMin = val;
        } else {
            if (yMax < val)
                yMax = val;
            if (yMin > val)
                yMin = val;
        }

        // add the entry
        yVals.add(e);
        return true;
    }

    @Override
    public boolean removeEntry(T e) {

        if (e == null)
            return false;

        if (vals == null)
            return false;

        // remove the entry
        boolean removed = vals.remove(e);

        if (removed) {
            calcYMinMax(0, vals.size());
        }

        return removed;
    }

    @Override
    public int getEntryIndex(Entry e) {
        return vals.indexOf(e);
    }

    @Override
    public T getEntryForXIndex(int xIndex, Rounding rounding) {

        int index = getEntryIndex(xIndex, rounding);
        if (index > -1)
            return vals.get(index);
        return null;
    }

    @Override
    public T getEntryForXIndex(int position) {
        return getEntryForXIndex(position, Rounding.CLOSEST);
    }

    @Override
    public T getEntryForIndex(int index) {
        return vals.get(index);
    }

    @Override
    public int getEntryIndex(float xIndex, Rounding rounding) {

        int low = 0;
        int high = vals.size() - 1;
        int closest = -1;

        while (low <= high) {
            int m = (high + low) / 2;

            if (xIndex == vals.get(m).getXVal()) {
                while (m > 0 && vals.get(m - 1).getXVal() == xIndex)
                    m--;

                return m;
            }

            if (xIndex > vals.get(m).getXVal())
                low = m + 1;
            else
                high = m - 1;

            closest = m;
        }

        if (closest != -1) {
            float closestXIndex = vals.get(closest).getXVal();
            if (rounding == Rounding.UP) {
                if (closestXIndex < xIndex && closest < vals.size() - 1) {
                    ++closest;
                }
            } else if (rounding == Rounding.DOWN) {
                if (closestXIndex > xIndex && closest > 0) {
                    --closest;
                }
            }
        }

        return closest;
    }

    @Override
    public float getYValForXIndex(int xIndex) {

        Entry e = getEntryForXIndex(xIndex);

        if (e != null && e.getXVal() == xIndex)
            return e.getYVal();
        else
            return Float.NaN;
    }

    @Override
    public float[] getYValsForXIndex(int xIndex) {

        List<T> entries = getEntriesForXIndex(xIndex);

        float[] yVals = new float[entries.size()];
        int i = 0;

        for (T e : entries)
            yVals[i++] = e.getYVal();

        return yVals;
    }

    /**
     * Returns all Entry objects at the given xIndex. INFORMATION: This method
     * does calculations at runtime. Do not over-use in performance critical
     * situations.
     *
     * @param xIndex
     * @return
     */
    @Override
    public List<T> getEntriesForXIndex(int xIndex) {

        List<T> entries = new ArrayList<T>();

        int low = 0;
        int high = vals.size() - 1;

        while (low <= high) {
            int m = (high + low) / 2;
            T entry = vals.get(m);

            if (xIndex == entry.getXVal()) {
                while (m > 0 && vals.get(m - 1).getXVal() == xIndex)
                    m--;

                high = vals.size();
                for (; m < high; m++) {
                    entry = vals.get(m);
                    if (entry.getXVal() == xIndex) {
                        entries.add(entry);
                    } else {
                        break;
                    }
                }

                break;
            } else {
                if (xIndex > entry.getXVal())
                    low = m + 1;
                else
                    high = m - 1;
            }
        }

        return entries;
    }

    /**
     * Determines how to round DataSet index values for
     * {@link DataSet#getEntryIndex(float, Rounding)} DataSet.getEntryIndex()}
     * when an exact x-index is not found.
     */

    public enum Rounding {
        UP,
        DOWN,
        CLOSEST,
    }
}
