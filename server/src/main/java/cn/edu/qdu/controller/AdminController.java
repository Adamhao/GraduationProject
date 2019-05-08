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
    public String toIndexPage(HttpSession session,Model model) {
        if(session.getAttribute("user") == null) {
            model.addAttribute("result","你没有权限访问该页面，登陆后重试!");
            return "login";
        }
        return "index";
    }

    @PostMapping("/login")
    public String index(Model model, Admin admin, HttpServletRequest request) {
        Admin temp = adminService.login(admin);
        model.addAttribute("login_email",admin.getEmail());
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

    @GetMapping("/toChangePasswordPage")
    public String toChangePasswordPage(HttpSession session,Model model) {
        Admin temp = (Admin) session.getAttribute("user");
        if(temp == null || temp.getEmail() == null) {
            model.addAttribute("result","你没有权限访问!");
            return "login";
        }
        return "change_password";
    }

    @PostMapping("/changePassword")
    public String changPass(String oldPass, String newPass, String confirmPass, HttpSession session,Model model) {
        Admin temp = (Admin) session.getAttribute("user");
        if(temp == null || temp.getEmail() == null) {
            model.addAttribute("result","你没有权限修改密码,重新登录后再试!");
            return "login";
        }
        try {
            adminService.changePassword(temp.getEmail(),oldPass,newPass,confirmPass);
            model.addAttribute("result","密码修改成功");
            return "login";
        } catch(Exception e) {
            model.addAttribute("result",e.getMessage());
            model.addAttribute("oldPass",oldPass);
            model.addAttribute("newPass",newPass);
            model.addAttribute("confirmPass",confirmPass);
            //返回修改密码页面，提示修改失败信息
            return "change_password";
        }
    }

    @GetMapping("/quit")
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/";
    }

}
