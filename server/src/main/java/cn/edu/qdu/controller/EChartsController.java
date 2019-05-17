package cn.edu.qdu.controller;

import cn.edu.qdu.echarts.bar.BarData;
import cn.edu.qdu.echarts.line.LineData;
import cn.edu.qdu.echarts.pie.PieData;
import cn.edu.qdu.service.ProductTypeService;
import cn.edu.qdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Adam on 2019/5/15 18:52.
 */
@Controller
@RequestMapping("/echarts")
public class EChartsController {

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private UserService userService;

    @PostMapping("/queryEachTypeSales")
    @ResponseBody
    public BarData queryEachTypeSales(){
        return productTypeService.queryEachTypeSales();
    }

    @PostMapping("/queryEachTypeQuantity")
    @ResponseBody
    public PieData queryEachTypeQuantity(){
        return productTypeService.queryEachTypeQuantity();
    }

    @PostMapping("/queryEveryDayIncreasedUserNum")
    @ResponseBody
    public LineData queryEveryDayIncreasedUserNum(){
        return userService.queryEveryDayIncreasedUserNum();
    }

}
