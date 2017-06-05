package com.hqyxjy.ldf.chartroidlib.component;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

/**
 * Created by ldf on 17/5/6.
 */

public class Entry implements Parcelable{
    private float yVal;
    private float xVal;
    private Object attachment;// 附件

    public Entry() {
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param yVal the y value (the actual value of the entry)
     * @param xVal 对应X轴值的链表中的index，所以此数值不能超过X轴链表的长度
     */
    public Entry(float yVal, float xVal) {
        this.yVal = yVal;
        this.xVal = xVal;
    }

    /**
     * A Entry represents one single entry in the chart.
     *
     * @param yVal the y value (the actual value of the entry)
     * @param xVal the corresponding index in the x value array (index on the
     *            x-axis of the chart, must NOT be higher than the length of the
     *            x-values String array)
     * @param data Spot for additional attachment this Entry represents.
     */
    public Entry(float yVal, float xVal, Object data) {
        this(yVal, xVal);
        this.attachment = data;
    }
    /**
     * returns the x-index the value of this object is mapped to
     *
     * @return
     */
    public float getXVal() {
        return xVal;
    }

    /**
     * sets the x-index for the entry
     *
     * @param x
     */
    public void setXVal(int x) {
        this.xVal = x;
    }

    /**
     * Returns the total value the entry represents.
     *
     * @return
     */
    public float getYVal() {
        return yVal;
    }

    /**
     * Sets the value for the entry.
     *
     * @param yVal
     */
    public void setYVal(float yVal) {
        this.yVal = yVal;
    }

    /**
     * Returns the attachment, additional information that this Entry represents, or
     * null, if no attachment has been specified.
     *
     * @return
     */
    public Object getAttachment() {
        return attachment;
    }

    /**
     * Sets additional attachment this Entry should represent.
     *
     * @param attachment
     */
    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    /**
     * returns an exact copy of the entry
     *
     * @return
     */
    public Entry copy() {
        Entry e = new Entry(yVal, xVal, attachment);
        return e;
    }

    /**
     * Compares value, xVal and attachment of the entries. Returns true if entries
     * are equal in those points, false if not. Does not check by hash-code like
     * it's done by the "equals" method.
     *
     * @param e
     * @return
     */
    public boolean equalTo(Entry e) {

        if (e == null)
            return false;

        if (e.attachment != this.attachment)
            return false;
        if (e.xVal != this.xVal)
            return false;

        if (Math.abs(e.yVal - this.yVal) > 0.00001f)
            return false;

        return true;
    }

    /**
     * returns a string representation of the entry containing x-index and value
     */
    @Override
    public String toString() {
        return "Entry, xVal: " + xVal + " yVal (sum): " + getYVal();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.yVal);
        dest.writeFloat(this.xVal);
        if (attachment != null) {
            if (attachment instanceof Parcelable) {
                dest.writeInt(1);
                dest.writeParcelable((Parcelable) this.attachment, flags);
            } else {
                throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable attachment");
            }
        } else {
            dest.writeInt(0);
        }
    }

    protected Entry(Parcel in) {
        this.yVal = in.readFloat();
        this.xVal = in.readInt();
        this.attachment = in.readParcelable(Object.class.getClassLoader());
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
