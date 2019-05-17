package cn.edu.qdu.mapper;

import cn.edu.qdu.echarts.line.LineData;
import cn.edu.qdu.echarts.line.LineItem;
import cn.edu.qdu.model.Admin;
import cn.edu.qdu.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 2019/5/5 20:39.
 */
@Repository
public interface UserMapper {

    List<User> queryAll(@Param("statement") String statement);

    int count();

    void disabledUsers(List<String> ids);

    void update(@Param("user") User user);

    List<LineItem> queryEveryDayIncreasedUserNum();
}
