package cn.edu.qdu.service;

import cn.edu.qdu.domin.Order;
import cn.edu.qdu.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bro_Dong on 2019/4/30.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> queryAll(Integer id,Integer page,Integer rows,Integer status) {
        PageHelper.startPage(page,rows);
        List<Order> data = orderMapper.queryAll(id,status);
        PageInfo<Order> pi = new PageInfo<>(data);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",data);
        result.put("total",pi.getTotal());
        return result;
    }

    @Override
    @Transactional
    public void updateGoodsNumDel(Integer id) {
        orderMapper.updateGoodsNumDel(id);
    }

    @Override
    @Transactional
    public void updateGoodsNumAdd(Integer id) {
        orderMapper.updateGoodsNumAdd(id);
    }

    @Override
    @Transactional
    public void deleteOne(Integer id) {
        orderMapper.deleteOne(id);
    }
}
