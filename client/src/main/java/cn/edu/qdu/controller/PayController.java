package cn.edu.qdu.controller;

import cn.edu.qdu.model.User;
import cn.edu.qdu.repository.UserRepository;
import cn.edu.qdu.util.UserUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import cn.edu.qdu.common.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    UserRepository userDao;

    @RequestMapping(value = "/success")
    public ModelAndView success(HttpServletRequest request,ModelAndView modelAndView) throws UnsupportedEncodingException, AlipayApiException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        String total_amount = params.get("total_amount");
        Double num = Double.valueOf(total_amount);
        Integer point = 0;
        if(num==1.00){
            point=10;
        }else if(num==4.50){
            point=50;
        }else if (num==8.80) {
            point=100;
        }else if(num==15.50){
            point=200;
        }else if(num==35.50){
            point=500;
        }
        String out_trade_no = params.get("out_trade_no");
        String[] ys = out_trade_no.split("Y");
        String a = ys[1];
        Integer b = Integer.valueOf(a);
        User user = userDao.findOne(b);
        user.setPoint(user.getPoint()+point);
        userDao.save(user);
        UserUtil.saveUserToSession(request.getSession(),user);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user/userProfile");
        return modelAndView;
    }

    @RequestMapping(value = "/in",method = RequestMethod.GET)
    public ModelAndView in(HttpSession session,ModelAndView modelAndView, String title, Double price, String desc){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date());
        User userFromSession = UserUtil.getUserFromSession(session);
        out_trade_no+=("Y"+userFromSession.getId());
        modelAndView.addObject("title",title);
        modelAndView.addObject("num",out_trade_no);
        modelAndView.addObject("desc",desc);
        modelAndView.addObject("price",price);
        modelAndView.setViewName("user/pay/pay");
        return modelAndView;
    }

    @RequestMapping(value = "/notify")
    public void notify1(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名


        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){

            }else if (trade_status.equals("TRADE_SUCCESS")){

            }
            logger.info("Alipay notify");

        }else {//验证失败
            logger.info("Alipay not notify");
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——

    }

}
