package cn.edu.qdu.mapper;

import cn.edu.qdu.domin.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bro_Dong on 2019/4/30.
 */
@Repository
public interface OrderMapper {
    List<Order> queryAll(@Param("userId") Integer userId,@Param("status") Integer status);

    void updateGoodsNumDel(Integer id);

    void updateGoodsNumAdd(Integer id);

    void deleteOne(Integer id);
}
