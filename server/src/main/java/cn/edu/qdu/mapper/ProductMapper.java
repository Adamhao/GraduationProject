package cn.edu.qdu.mapper;

import cn.edu.qdu.model.Product;
import cn.edu.qdu.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 2019/5/5 20:39.
 */
@Repository
public interface ProductMapper {

    List<Product> queryAll(@Param("statement") String statement);

    int count();

    void disabledProducts(List<String> ids);

    void update(@Param("product") Product product);

}
