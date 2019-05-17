package cn.edu.qdu.mapper;

import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.model.ProductTypeSales;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 2019/5/15 12:07.
 */
@Repository
public interface ProductTypeSalesMapper {

    List<ProductTypeSales> queryAll();
}
