package com.hqyxjy.ldf.chartroidlib.component;

/**
 * Created by ldf on 17/5/6.
 */

public class Range {
    private float start = 0;

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }

    private float end = 0;

    public Range(float start , float end){
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false;
        }
        if (obj instanceof Range) {
            if(start == ((Range) obj).start && end == ((Range) obj).end ) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Range obj) {
        if (start > ((Range) obj).start && end < ((Range) obj).end) {
            return true;
        }
        return false;
    }


}
