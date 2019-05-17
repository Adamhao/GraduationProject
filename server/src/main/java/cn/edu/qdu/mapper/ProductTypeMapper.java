package cn.edu.qdu.mapper;

import cn.edu.qdu.echarts.pie.PieItem;
import cn.edu.qdu.model.ProductType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 2019/5/15 12:07.
 */
@Repository
public interface ProductTypeMapper {

    List<ProductType> queryAll();

    //查询每类下商品数量
    List<PieItem> queryEachTypeQuantity();
}
