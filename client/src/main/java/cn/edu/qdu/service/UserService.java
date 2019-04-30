package cn.edu.qdu.service;


import cn.edu.qdu.domin.User;

import javax.mail.MessagingException;

/**
 * Created by Bro_Dong on 2019/4/25.
 */
public interface UserService {
    void insert(User user) throws MessagingException;

    User queryByUsername(String username);

    void updateStatus(Integer id);

    void updatePassword(String username, String password);

    void updateActivation_code(String username, String activation_code, String email) throws MessagingException;
}
