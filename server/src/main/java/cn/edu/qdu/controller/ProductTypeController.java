package cn.edu.qdu.controller;

import cn.edu.qdu.echarts.bar.BarData;
import cn.edu.qdu.model.ProductType;
import cn.edu.qdu.service.ProductTypeService;
import cn.edu.qdu.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/15 12:13.
 */
@Controller
@RequestMapping("/producttype")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/")
    public String toUserPage() {
        return "product";
    }

    @PostMapping("/queryAll")
    @ResponseBody
    public List<ProductType> queryAll(){
        return productTypeService.queryAll();
    }

}
