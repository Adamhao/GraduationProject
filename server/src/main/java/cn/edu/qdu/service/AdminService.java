package cn.edu.qdu.service;

import cn.edu.qdu.model.Admin;

/**
 * Created by Adam on 2019/5/5 20:01.
 */
public interface AdminService {

    Admin login(Admin inputAdmin);

    void changePassword(String email,String oldPassword,String newPassword,String confirmPassword);

}
