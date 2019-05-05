package cn.edu.qdu.controller;

import cn.edu.qdu.model.Admin;
import cn.edu.qdu.model.Article;
import cn.edu.qdu.service.AdminService;
import cn.edu.qdu.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Adam on 2019/5/4 15:06.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String toLoginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String toIndexPage() {
        return "index";
    }

    @PostMapping("/login")
    public String index(Model model, Admin admin, HttpServletRequest request) {
        Admin temp = adminService.login(admin);
        if(temp == null) {
            model.addAttribute("result","用户名或密码有误!");
            return "login";
        }
        if(temp.getState() == null || temp.getState() < 0) {
            model.addAttribute("result","状态异常，联系Adam解决!");
            return "login";
        }
        temp.setPassword(null);
        request.getSession().setAttribute("user",temp);
        return "redirect:index";
    }

    @GetMapping("/changePassword")
    @ResponseBody
    public String changPass(String oldPass, String newPass, String confirmPass, HttpSession session) {
        Admin temp = (Admin) session.getAttribute("user");
        adminService.changePassword(temp.getEmail(),oldPass,newPass,confirmPass);
        return "密码修改成功";
    }

    @GetMapping("/quit")
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

}
