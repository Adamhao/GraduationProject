package cn.edu.qdu.util;

import cn.edu.qdu.vo.FilterParam;
import cn.edu.qdu.vo.FilterRule;
import cn.edu.qdu.vo.SearchParam;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索时字符与操作时符号的映射
 * Created by Adam on 2019/5/12 1:21.
 */
public class SearchMapping {

    private static final Map<String, String> dbOperatorMapping = new HashMap<>();

    /*
    public static final String eq = " = ";//equal
    public static final String ne = " = ";//not equal
    public static final String bw = " = ";//begin with
    public static final String bn = " = ";//does not begin with
    public static final String ew = " = ";//ends with
    public static final String en = " = ";//does not ends with
    public static final String cn = " = ";//contains
    public static final String nc = " = ";//does not contains
    public static final String nu = " = ";//is null
    public static final String nn = " = ";//is not null
    public static final String in = " = ";//is in
    public static final String ni = " = ";//is not in
    */
    static {
        dbOperatorMapping.put("eq", " = {} ");//equal
        dbOperatorMapping.put("ne", " != {} ");//not equal
        dbOperatorMapping.put("bw", " like '{}%' ");//begin with e.g: adam%
        //dbOperatorMapping.put("bn"," = ");//does not begin with
        dbOperatorMapping.put("lt", " < {} ");//less than
        dbOperatorMapping.put("le", " <= {} ");//less or equal
        dbOperatorMapping.put("gt", " > {} ");//great than
        dbOperatorMapping.put("ge", " >= {} ");//greater or equal
        dbOperatorMapping.put("ew", " like '%{}' ");//ends with
        //dbOperatorMapping.put("en"," = ");//does not ends with
        //dbOperatorMapping.put("cn"," = ");//contains
        //dbOperatorMapping.put("nc"," = ");//does not contains
        //dbOperatorMapping.put("nu"," = ");//is null
        //dbOperatorMapping.put("nn"," = ");//is not null
        //dbOperatorMapping.put("in"," = ");//is in
        //dbOperatorMapping.put("ni"," = ");//is not in
    }

    public static String getOperator(FilterRule rule) {
        if ("id".equals(rule.getField()) || "".equals(rule.getData())) {
            return "";
        }
        String result = dbOperatorMapping.get(rule.getOp());
        return rule.getField() + result.replace("{}", getFormatStateValue(rule.getData()));
    }

    private static String getFormatStateValue(String value) {
        if ("已封禁".equals(value)) {
            return "-1";
        } else if ("正常".equals(value)) {
            return "1";
        } else if ("未激活".equals(value)) {
            return "0";
        } else {
            return value;
        }
    }

    public static String getFormatStatement(String tableName, SearchParam searchParam) {
        StringBuffer sb = new StringBuffer("select * from ");
        sb.append(tableName);
        if (searchParam.isSearch() == true) {
            FilterParam param = JsonUtil.toObject(searchParam.getFilters(), new TypeReference<FilterParam>() {});
            boolean isFirst = true;
            for (FilterRule rule : param.getRules()) {
                if (!"".equals(getOperator(rule))) {
                    if (isFirst) {
                        isFirst = false;
                        sb.append(" where ");
                    }
                    sb.append(getOperator(rule)).append(param.getGroupOp()).append(" ");
                }
            }

            if (sb.toString().endsWith(param.getGroupOp() + " ")) {
                sb = new StringBuffer(sb.substring(0, sb.length() - param.getGroupOp().length() - 1));
            }
        }
        /*sidx: username
        sord: asc*/
        sb.append(" order by ").append(searchParam.getSidx()).append(" ").append(searchParam.getSord());
        System.out.println(sb);
        return sb.toString();
    }

}
