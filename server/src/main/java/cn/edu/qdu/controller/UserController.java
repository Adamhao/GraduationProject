package cn.edu.qdu.controller;

import cn.edu.qdu.model.User;
import cn.edu.qdu.service.UserService;
import cn.edu.qdu.util.JsonUtil;
import cn.edu.qdu.vo.SearchParam;
import cn.edu.qdu.vo.UserUpdateParam;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/10 21:36.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String toUserPage() {
        return "user";
    }

    @PostMapping("/queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(SearchParam searchParam){
        Map<String, Object> map = userService.queryAll(searchParam);
        return map;
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateUser(UserUpdateParam param) {
        userService.update(param);
        return "success";
    }
}
