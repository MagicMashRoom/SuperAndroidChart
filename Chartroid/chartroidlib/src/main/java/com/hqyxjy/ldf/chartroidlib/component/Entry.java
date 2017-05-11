package com.hqyxjy.ldf.chartroidlib.component;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ldf on 17/5/6.
 */

public class Entry implements Parcelable{
    private float xVal;
    private float xIndex;
    private float data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.xVal);
        dest.writeFloat(this.xIndex);
        dest.writeFloat(this.data);
    }

    public Entry() {
    }

    protected Entry(Parcel in) {
        this.xVal = in.readFloat();
        this.xIndex = in.readFloat();
        this.data = in.readFloat();
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
