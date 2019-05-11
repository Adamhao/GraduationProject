package cn.edu.qdu.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2019/5/12 0:28.
 */
public class StringUtil {

    public static List<String> getIdListByString(String ids) {
        List<String> list = new ArrayList<>();
        if(ids != null && !"".equals(ids.trim())) {
            for(String temp : ids.split(",")) {
                list.add(temp.trim());
            }
        }
        return list;
    }

}
