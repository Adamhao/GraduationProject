package cn.edu.qdu.controller;

import cn.edu.qdu.domin.Order;
import cn.edu.qdu.domin.User;
import cn.edu.qdu.service.OrderService;
import cn.edu.qdu.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Bro_Dong on 2019/4/30.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/queryAll")
    @ResponseBody
    public Page<Order> queryAll(HttpSession session,Integer pageNumber,Integer pageSize,Integer status){
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = orderService.queryAll(user.getId(),pageNumber,pageSize,status);
        Page<Order> page = new Page<>();
        page.setRows((List<Order>) map.get("rows"));
        page.setTotal(Integer.parseInt(String.valueOf(map.get("total"))));
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        return page;
    }

    @PostMapping("/deleteOne")
    @ResponseBody
    public String deleteOne(Integer id,Integer goodsNumber){
        if (goodsNumber == 1){
            return "false";
        }
        orderService.updateGoodsNumDel(id);
        return "true";
    }

    @PostMapping("/addOne")
    @ResponseBody
    public String addOne(Integer id,Integer goodsNumber){
        orderService.updateGoodsNumAdd(id);
        return "true";
    }

    @PostMapping("/deleteOrder")
    @ResponseBody
    public String deleteOrder(Integer id){
        orderService.deleteOne(id);
        return "true";
    }
}
