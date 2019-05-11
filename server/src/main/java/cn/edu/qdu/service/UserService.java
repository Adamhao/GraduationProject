package cn.edu.qdu.service;

import cn.edu.qdu.vo.SearchParam;
import cn.edu.qdu.vo.UserUpdateParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/11 16:52.
 */
public interface UserService {

    Map<String,Object> queryAll(SearchParam param);

    void update(UserUpdateParam param);
}
