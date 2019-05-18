package cn.edu.qdu.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import cn.edu.qdu.common.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

    @RequestMapping(value = "/pay1",method = RequestMethod.GET)
    public void pay1(String title,Integer price ,String desc, HttpServletResponse response) throws IOException, AlipayApiException {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date());

        //付款金额，必填
        String total_amount = price+"";
        //订单名称，必填
        String subject = title;
        //商品描述，可空
        String body = desc;

        // 付款金额，必填
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // System.out.println(result);
//        AlipayConfig.logResult(result);// 记录支付日志
        response.setContentType("text/html; charset=gbk");
        PrintWriter out = response.getWriter();
        out.println(result);
        out.close();
    }

    @RequestMapping(value = "/notify")
    public void pay2(HttpServletRequest request){
        System.out.println("接收到支付宝异步返回的信息-------------------------");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String, String[]> map:parameterMap.entrySet()){
            System.out.println(map.getKey()+":"+map.getValue());
            System.out.println("-----分割线----------");
            String[] values = map.getValue();
            for (String v : values) {
                System.out.println(v);
            }
        }
    }

    @RequestMapping(value = "/success")
    public String success(){
        return "redirect:/success";
    }

    @RequestMapping(value = "/in",method = RequestMethod.GET)
    public ModelAndView in(ModelAndView modelAndView,String title,Double price,String desc){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date());
        modelAndView.addObject("title",title);
        modelAndView.addObject("num",out_trade_no);
        modelAndView.addObject("desc",desc);
        modelAndView.addObject("price",price);
        modelAndView.setViewName("user/pay/pay");
        return modelAndView;
    }

}
