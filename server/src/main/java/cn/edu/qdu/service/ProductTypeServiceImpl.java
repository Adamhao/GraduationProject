package cn.edu.qdu.service;

import cn.edu.qdu.echarts.bar.BarData;
import cn.edu.qdu.echarts.pie.PieData;
import cn.edu.qdu.echarts.pie.PieItem;
import cn.edu.qdu.mapper.ProductMapper;
import cn.edu.qdu.mapper.ProductTypeMapper;
import cn.edu.qdu.mapper.ProductTypeSalesMapper;
import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.model.ProductTypeSales;
import cn.edu.qdu.vo.ProductUpdateParam;
import cn.edu.qdu.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/15 12:10.
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Autowired
    private ProductTypeSalesMapper productTypeSalesMapper;

    @Override
    public List<ProductType> queryAll() {
        return productTypeMapper.queryAll();
    }

    @Override
    public BarData queryEachTypeSales() {
        List<ProductTypeSales> result = productTypeSalesMapper.queryAll();
        BarData data = new BarData();
        List<String> xAxisData = new ArrayList<>();
        List<Integer> seriesData = new ArrayList<>();
        data.setxAxisData(xAxisData);
        data.setSeriesData(seriesData);
        for(ProductTypeSales temp : result) {
            xAxisData.add(temp.getName());
            seriesData.add(temp.getQuantity());
        }
        return data;
    }

    @Override
    public PieData queryEachTypeQuantity() {
        List<PieItem> seriesData = productTypeMapper.queryEachTypeQuantity();
        PieData data = new PieData();
        List<String> legendData = new ArrayList<>();
        data.setLegendData(legendData);
        data.setSeriesData(seriesData);
        for(PieItem temp : seriesData) {
            legendData.add(temp.getName());
        }
        return data;
    }
}
