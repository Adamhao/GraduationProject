package cn.edu.qdu.controller;


import cn.edu.qdu.domin.User;
import cn.edu.qdu.service.UserService;
import cn.edu.qdu.utils.JuHe;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by Bro_Dong on 2019/4/24.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(User user){
        try {
            userService.insert(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        User user = userService.queryByUsername(username);
        if (user == null){
            model.addAttribute("msg","该用户名未注册");
            return "user/login";
        }else if (!user.getPassword().equals(password)){
            model.addAttribute("msg","用户名密码错误");
            return "user/login";
        }else if (user.getStatus() == 0){
            model.addAttribute("msg","该用户名尚未激活，请到邮箱内激活");
            return "user/login";
        }
        session.setAttribute("user",user);
        return "redirect:/index";
    }

    //检查用户名是否重复
    @GetMapping("/checkName")
    @ResponseBody
    public String checkName(String username){
        User user = userService.queryByUsername(username);
        if(user == null){
            return "true";
        }
        return "false";
    }

    //检查用户名是否存在
    @GetMapping("/checkNameIsTrue")
    @ResponseBody
    public String checkNameIsTrue(String username){//验证用户是否存在
        User user = userService.queryByUsername(username);
        if(user == null){
            return "false";
        }
        return "true";
    }

    //检查手机号是否匹配
    @GetMapping("/checkPhoneIsTrue")
    @ResponseBody
    public String checkPhoneIsTrue(String username,String phone){
        User user = userService.queryByUsername(username);
        if(phone.equals(user.getPhone())){
            return "true";
        }
        return "false";
    }

    //检查邮箱是否匹配
    @GetMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(String username,String email){
        User user = userService.queryByUsername(username);
        if(email.equals(user.getEmail())){
            return "true";
        }
        return "false";
    }

    @PostMapping("/checkPhone")
    @ResponseBody
    public JSONObject checkPhone(String phone,HttpSession session){
        String checkCode = vcode();
        System.out.println(checkCode);
        JSONObject result = JuHe.mobileQuery(phone,checkCode);
        if (result != null && result.getInteger("error_code")==0){
            session.setAttribute("checkCode",checkCode);
        }
        return result;
    }

    @GetMapping("/checkCode")
    @ResponseBody
    public String checkCode(String proveCode,HttpSession session){
        String code = (String) session.getAttribute("checkCode");
        if(proveCode.equals(code)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/activation")
    public String activation (String username,String activationCode,Model model){
        User user = userService.queryByUsername(username);
        if (user.getActivation_code().equals(activationCode)){
            userService.updateStatus(user.getId());
            model.addAttribute("status","激活成功，请登录");
        }else {
            model.addAttribute("status","激活失败，请到邮箱内点击链接进行账号激活");
        }
        return "user/login";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(String username,String password,String email){
        userService.updatePassword(username, password);
        if (email != null) {
            String activation_code = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                userService.updateActivation_code(username,activation_code,email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    /**
     * 生成6位随机数验证码
     * @return
     */
    public String vcode(){
        StringBuffer vcode = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            vcode.append((int)(Math.random() * 9));
        }
        return vcode.toString();
    }
}
