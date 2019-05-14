package cn.edu.qdu.util;

import cn.edu.qdu.vo.SearchParam;

/**
 * Created by Adam on 2019/5/12 1:03.
 */
public class SQLUtil {

    public static String getFormatStatement(String tableName, SearchParam param) {
        StringBuffer sb = new StringBuffer("select * from ");
        sb.append(tableName);
        if(param.isSearch() == false) {
            /*sidx: username
            sord: asc*/
            sb.append(" order by ").append(param.getSidx()).append(" ").append(param.getSord());
        } else {

        }
        return sb.toString();
    }

}
