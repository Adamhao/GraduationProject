package cn.edu.qdu.service;

import cn.edu.qdu.echarts.bar.BarData;
import cn.edu.qdu.echarts.pie.PieData;
import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.vo.ProductUpdateParam;
import cn.edu.qdu.vo.SearchParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/11 16:52.
 */
public interface ProductTypeService {

    List<ProductType> queryAll();

    BarData queryEachTypeSales();

    PieData queryEachTypeQuantity();
}
