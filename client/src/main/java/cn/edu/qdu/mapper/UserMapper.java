package cn.edu.qdu.mapper;


import cn.edu.qdu.domin.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Bro_Dong on 2019/4/25.
 */
@Repository
public interface UserMapper {
    void insert(User user);

    User queryByUsername(String username);

    void updateStatusById(Integer id);

    void updatePassword(@Param("username") String username, @Param("password") String password);

    void updateActivation_code(@Param("username") String username, @Param("activation_code") String activation_code);
}
