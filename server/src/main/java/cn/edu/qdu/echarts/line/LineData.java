package cn.edu.qdu.echarts.line;

import java.util.List;

/**
 * Created by Adam on 2019/5/15 21:10.
 */
public class LineData {

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
