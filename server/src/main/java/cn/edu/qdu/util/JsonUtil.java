package cn.edu.qdu.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    //定义ObjectMapper对象
    private static ObjectMapper mapper;
    //定义处理时间格式化
    private static DateFormat dateFormat;

    static {
        //创建ObjectMapper对象
        mapper = new ObjectMapper();
        //创建格式化对象
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //处理时间
        mapper.setDateFormat(dateFormat);
    }

    //将对象转换成json字符串   @ResponseBody
    public static String toStr(Object object) {
        String json = null;

        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    //将json字符串转换成对象 @RequestBody
    public static <T> T toObject(String json, TypeReference<T> type) {
        T t = null;

        try {
            t = mapper.readValue(json,type);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "etoak");
        map.put("age", 10);
        map.put("date", new Date());

        System.out.println(map);

        List<Map<String,Object>> list = new ArrayList<>();
        list.add(map);

        String json = JsonUtil.toStr(list);
        System.out.println(json);

        json = "[{\"date\":\"2018-11-02 09:40:44\",\"name\":\"etoak\",\"age\":10}]";

        List<Map<String,Object>> list01 = JsonUtil.toObject(json,
                new TypeReference<List<Map<String,Object>>>() {});


        Map<String,Object> result = list01.get(0);
        System.out.println(result.get("date"));
        System.out.println(result.get("name"));
        System.out.println(result.get("age"));
    }

}
