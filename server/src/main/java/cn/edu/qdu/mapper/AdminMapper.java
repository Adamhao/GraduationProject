package cn.edu.qdu.mapper;

import cn.edu.qdu.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Adam on 2019/5/5 20:39.
 */
@Repository
public interface AdminMapper {

    Admin login(@Param("admin") Admin admin);

    int changePassword(@Param("email") String email,@Param("password") String password);

    String getPasswordByEmail(@Param("email") String email);
}
