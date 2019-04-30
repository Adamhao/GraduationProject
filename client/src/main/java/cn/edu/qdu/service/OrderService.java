package cn.edu.qdu.service;

import java.util.Map;

/**
 * Created by Bro_Dong on 2019/4/30.
 */
public interface OrderService {
    Map<String,Object> queryAll(Integer id,Integer page,Integer rows,Integer status);

    void updateGoodsNumDel(Integer id);

    void updateGoodsNumAdd(Integer id);

    void deleteOne(Integer id);
}
