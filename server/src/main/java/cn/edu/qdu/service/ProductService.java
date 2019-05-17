package cn.edu.qdu.service;

import cn.edu.qdu.vo.ProductUpdateParam;
import cn.edu.qdu.vo.SearchParam;
import cn.edu.qdu.vo.UserUpdateParam;

import java.util.Map;

/**
 * Created by Adam on 2019/5/11 16:52.
 */
public interface ProductService {

    Map<String,Object> queryAll(SearchParam param);

    void update(ProductUpdateParam param);
}
