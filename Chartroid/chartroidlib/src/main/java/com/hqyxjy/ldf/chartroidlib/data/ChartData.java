package com.hqyxjy.ldf.chartroidlib.data;

import com.hqyxjy.ldf.chartroidlib.component.Entry;
import com.hqyxjy.ldf.chartroidlib.data.iset.IDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldf on 17/5/17.
 */

public abstract class ChartData<T extends IDataSet<? extends Entry>>{
    /**
     * array that holds all DataSets the ChartData object represents
     */
    protected List<T> mDataSets;

    /**
     * Default constructor.
     */
    public ChartData() {
        mDataSets = new ArrayList<T>();
    }

    /**
     * Constructor taking single or multiple DataSet objects.
     *
     * @param dataSets
     */
    public ChartData(T... dataSets) {
        mDataSets = arrayToList(dataSets);
        notifyDataChanged();
    }

    /**
     * Created because Arrays.asList(...) does not support modification.
     *
     * @param array
     * @return
     */
    private List<T> arrayToList(T[] array) {

        List<T> list = new ArrayList<>();

        for (T set : array) {
            list.add(set);
        }

        return list;
    }

    /**
     * Call this method to let the ChartData know that the underlying data has
     * changed. Calling this performs all necessary recalculations needed when
     * the contained data has changed.
     */
    public void notifyDataChanged() {
        calcMinMax();
    }

    private void calcMinMax() {

    }
}
