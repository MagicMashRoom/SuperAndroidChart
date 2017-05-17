package com.hqyxjy.ldf.chartroidlib.component;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

/**
 * Created by ldf on 17/5/6.
 */

public class Entry implements Parcelable{
    private float val;
    private int xIndex;
    private Object data;

    public Entry() {
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param val the y value (the actual value of the entry)
     * @param xIndex the corresponding index in the x value array (index on the
     *            x-axis of the chart, must NOT be higher than the length of the
     *            x-values String array)
     */
    public Entry(float val, int xIndex) {
        this.val = val;
        this.xIndex = xIndex;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param val the y value (the actual value of the entry)
     * @param xIndex the corresponding index in the x value array (index on the
     *            x-axis of the chart, must NOT be higher than the length of the
     *            x-values String array)
     * @param data Spot for additional data this Entry represents.
     */
    public Entry(float val, int xIndex, Object data) {
        this(val, xIndex);
        this.data = data;
    }
    /**
     * returns the x-index the value of this object is mapped to
     *
     * @return
     */
    public int getXIndex() {
        return xIndex;
    }

    /**
     * sets the x-index for the entry
     *
     * @param x
     */
    public void setXIndex(int x) {
        this.xIndex = x;
    }

    /**
     * Returns the total value the entry represents.
     *
     * @return
     */
    public float getVal() {
        return val;
    }

    /**
     * Sets the value for the entry.
     *
     * @param val
     */
    public void setVal(float val) {
        this.val = val;
    }

    /**
     * Returns the data, additional information that this Entry represents, or
     * null, if no data has been specified.
     *
     * @return
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets additional data this Entry should represent.
     *
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * returns an exact copy of the entry
     *
     * @return
     */
    public Entry copy() {
        Entry e = new Entry(val, xIndex, data);
        return e;
    }

    /**
     * Compares value, xIndex and data of the entries. Returns true if entries
     * are equal in those points, false if not. Does not check by hash-code like
     * it's done by the "equals" method.
     *
     * @param e
     * @return
     */
    public boolean equalTo(Entry e) {

        if (e == null)
            return false;

        if (e.data != this.data)
            return false;
        if (e.xIndex != this.xIndex)
            return false;

        if (Math.abs(e.val - this.val) > 0.00001f)
            return false;

        return true;
    }

    /**
     * returns a string representation of the entry containing x-index and value
     */
    @Override
    public String toString() {
        return "Entry, xIndex: " + xIndex + " val (sum): " + getVal();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.val);
        dest.writeInt(this.xIndex);
        if (data != null) {
            if (data instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) this.data, flags);
            } else {
                throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
            }
        } else {
            dest.writeInt(0);
        }
    }

    protected Entry(Parcel in) {
        this.val = in.readFloat();
        this.xIndex = in.readInt();
        this.data = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
}
