package cn.edu.qdu.controller;

import cn.edu.qdu.service.ProductService;
import cn.edu.qdu.service.UserService;
import cn.edu.qdu.vo.ProductUpdateParam;
import cn.edu.qdu.vo.SearchParam;
import cn.edu.qdu.vo.UserUpdateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Adam on 2019/5/10 21:36.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String toUserPage() {
        return "product";
    }

    @PostMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(SearchParam searchParam, HttpServletRequest request){
        searchParam.setSearch(Boolean.parseBoolean(request.getParameter("_search")));
        Map<String, Object> map = productService.queryAll(searchParam);
        return map;
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateUser(ProductUpdateParam param) {
        productService.update(param);
        return "success";
    }
}
