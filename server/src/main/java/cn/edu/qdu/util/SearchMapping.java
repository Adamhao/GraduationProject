package cn.edu.qdu.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索时字符与操作时符号的映射
 * Created by Adam on 2019/5/12 1:21.
 */
public class SearchMapping {

    private static final Map<String,String> dbOperatorMapping = new HashMap<>();

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
        dbOperatorMapping.put("eq"," = ");
        dbOperatorMapping.put("ne"," != ");
        dbOperatorMapping.put("bw"," = ");//e.g: adam%
        dbOperatorMapping.put("bn"," = ");
        dbOperatorMapping.put("ew"," = ");
        dbOperatorMapping.put("en"," = ");
        dbOperatorMapping.put("cn"," = ");
        dbOperatorMapping.put("nc"," = ");
        dbOperatorMapping.put("nu"," = ");
        dbOperatorMapping.put("nn"," = ");
        dbOperatorMapping.put("in"," = ");
        dbOperatorMapping.put("ni"," = ");
    }

    public static String getOperator(String abbr) {
        return dbOperatorMapping.get(abbr);
    }

}
