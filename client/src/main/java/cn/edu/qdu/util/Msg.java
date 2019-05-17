package cn.edu.qdu.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回类
 */
public class Msg {
//    状态码 100-成功 200-失败
    private int code;
////    展示信息
    private String msg;
//    用户要返回给游览器的数据
    private Map<String,Object> data = new HashMap<>();


    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
}  public static Msg success(String message){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg(message);
        return result;
}
    public static Msg failure(){
        Msg result = new Msg();
        result.setCode(500);
        result.setMsg("处理失败");
        return result;
    }
    public static Msg failure(String message){
        Msg result = new Msg();
        result.setCode(500);
        result.setMsg(message);
        return result;
    }

    public Msg put(String key, Object value) {
        this.getData().put(key,value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
