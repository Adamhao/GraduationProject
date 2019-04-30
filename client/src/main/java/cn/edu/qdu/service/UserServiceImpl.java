package cn.edu.qdu.service;


import cn.edu.qdu.domin.User;
import cn.edu.qdu.mapper.UserMapper;
import cn.edu.qdu.utils.SendQQMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.UUID;

/**
 * Created by Bro_Dong on 2019/4/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(User user) throws MessagingException {
        String activation_code = UUID.randomUUID().toString().replaceAll("-", "");
        user.setStatus(0);
        user.setActivation_code(activation_code);
        String context = "请点击该网址进行账号激活\nhttp://localhost:8080/user/activation?username="+user.getUsername()+"&activationCode="+activation_code;
        SendQQMailUtil.sendEmail(user.getEmail(),context);
        userMapper.insert(user);
    }

    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id) {
        userMapper.updateStatusById(id);
    }

    @Override
    @Transactional
    public void updatePassword(String username, String password) {
        userMapper.updatePassword(username,password);
    }

    @Override
    @Transactional
    public void updateActivation_code(String username, String activation_code, String email) throws MessagingException {
        userMapper.updateActivation_code(username,activation_code);
        String context = "请点击该网址重新进行账号激活\nhttp://localhost:8080/user/activation?username="+username+"&activationCode="+activation_code;
        SendQQMailUtil.sendEmail(email,context);
    }
}
