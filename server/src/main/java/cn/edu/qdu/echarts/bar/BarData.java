package cn.edu.qdu.echarts.bar;

import java.util.List;

/**
 * Created by Adam on 2019/5/15 18:20.
 */
public class BarData {

    private List<String> xAxisData;
    private List<Integer> seriesData;

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<Integer> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<Integer> seriesData) {
        this.seriesData = seriesData;
    }
}
