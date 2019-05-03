package cn.edu.qdu.test;

import cn.edu.qdu.util.JsonUtil;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件发送功能示例
 * Created by Adam on 2019/5/3 22:39.
 */
public class SendEmailTest {

    public static void main(String[] args) throws HttpProcessException {

        //邮件接收地址
        String url = "http://localhost:8888/api/sendEmail/send";

        Map<String,Object> params = new HashMap<>(3);
        params.put("subject","菜虚鲲最新歌曲推荐");//邮件标题
        params.put("recipient","184103164@qq.com");//收件地址
        params.put("content","只因你太美");//内容

        //最简单的使用
        String response = HttpClientUtil.post(HttpConfig.custom().json(JsonUtil.toStr(params)).url(url));
        System.out.println(response);

        Map<String,String> map = JsonUtil.toObject(response,new TypeReference<Map<String,String>>() {});
        //发送状态
        System.out.println(map.get("code"));
        //返回的详情信息
        System.out.println(map.get("content"));
    }
}
