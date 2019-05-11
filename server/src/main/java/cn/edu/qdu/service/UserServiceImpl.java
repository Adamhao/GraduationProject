package cn.edu.qdu.service;

import cn.edu.qdu.mapper.UserMapper;
import cn.edu.qdu.model.User;
import cn.edu.qdu.util.SQLUtil;
import cn.edu.qdu.util.StringUtil;
import cn.edu.qdu.vo.SearchParam;
import cn.edu.qdu.vo.UserUpdateParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adam on 2019/5/11 17:45.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryAll(SearchParam param) {
        String sql = SQLUtil.getFormatStatement("t_user",param);
        PageHelper.startPage(param.getPage(),param.getRows());
        List<User> data = userMapper.queryAll(sql);
        int count = userMapper.count();
        PageInfo<User> pi = new PageInfo<>(data);
        Map<String,Object> result = new HashMap<>();
        result.put("rows",data);
        result.put("total",(count % param.getRows()) == 0 ? count / param.getRows() : count / param.getRows()+1);//总页数
        result.put("page",param.getPage());//当前页
        result.put("records",count);//总记录数
        return result;
    }

    @Override
    public void update(UserUpdateParam param) {
        if(param == null || param.getOper() == null || param.getId() == null) {
            return;
        }
        if("del".equals(param.getOper())) {
            userMapper.disabledUsers(StringUtil.getIdListByString(param.getId()));
        } else if("edit".equals(param.getOper())) {
            userMapper.update(new User(Integer.parseInt(param.getId()),param.getState(),param.getNote()));
        }

    }

}
