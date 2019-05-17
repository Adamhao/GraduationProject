package cn.edu.qdu.echarts.pie;

import java.util.List;

/**
 * Created by Adam on 2019/5/15 18:54.
 */
public class PieData {

    private List<String> legendData;
    private List<PieItem> seriesData;

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<PieItem> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<PieItem> seriesData) {
        this.seriesData = seriesData;
    }
}
