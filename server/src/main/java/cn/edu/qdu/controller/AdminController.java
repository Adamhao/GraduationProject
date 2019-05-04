package cn.edu.qdu.controller;

import cn.edu.qdu.model.Admin;
import cn.edu.qdu.model.Article;
import cn.edu.qdu.util.AccountUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Adam on 2019/5/4 15:06.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/")
    public String toLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String index(Model model, Admin admin) {
        AccountUtil.isRightPasswordFormat(admin.getPassword());
        System.out.println(admin.getEmail());
        System.out.println(admin.getPassword());
        return "login";
    }

}
