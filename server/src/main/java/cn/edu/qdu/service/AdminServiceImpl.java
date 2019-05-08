package cn.edu.qdu.service;

import cn.edu.qdu.exception.NotAuthorizedException;
import cn.edu.qdu.exception.PasswordFormatWrongException;
import cn.edu.qdu.exception.PasswordNotCorrectException;
import cn.edu.qdu.mapper.AdminMapper;
import cn.edu.qdu.model.Admin;
import cn.edu.qdu.util.AccountUtil;
import cn.edu.qdu.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Adam on 2019/5/5 20:26.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin inputAdmin) {
        if(inputAdmin == null
                || inputAdmin.getEmail() == null
                || inputAdmin.getPassword() == null) {
            return null;
        }
        inputAdmin.setPassword(MD5Util.MD5Encode(inputAdmin.getPassword()));
        return adminMapper.login(inputAdmin);
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword,String confirmPassword) {
        if(email == null) {
            throw new NotAuthorizedException("没有权限");
        }
        if(oldPassword == null || "".equals(oldPassword.trim())) {
            throw new PasswordFormatWrongException("输入老密码");
        }
        if(newPassword == null || "".equals(newPassword.trim())) {
            throw new PasswordFormatWrongException("输入新密码");
        }
        if(confirmPassword == null || "".equals(confirmPassword.trim())) {
            throw new PasswordFormatWrongException("输入确认密码");
        }

        if(!confirmPassword.equals(newPassword)) {
            throw new PasswordNotCorrectException("两次密码不一致");
        }

        if(oldPassword.equals(confirmPassword)) {
            throw new PasswordFormatWrongException("新密码不要与旧密码一致");
        }

        AccountUtil.isRightPasswordFormat(confirmPassword);

        String password = adminMapper.getPasswordByEmail(email);
        if(!AccountUtil.isPasswordRight(password,oldPassword)) {
            throw new PasswordNotCorrectException("旧密码有误");
        }

        adminMapper.changePassword(email,MD5Util.MD5Encode(confirmPassword));

    }
}
