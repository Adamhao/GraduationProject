package cn.edu.qdu;

import cn.edu.qdu.email.OhMyEmail;
import cn.edu.qdu.exception.SendMailException;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.edu.qdu.email.OhMyEmail.SMTP_163;
import static cn.edu.qdu.email.OhMyEmail.SMTP_ENT_QQ;
import static cn.edu.qdu.email.OhMyEmail.SMTP_QQ;

/**
 * 发送邮件测试
 *
 * @author biezhi
 *         2017/5/30
 */
public class OhMyEmailTest {

    // 该邮箱修改为你需要测试的邮箱地址
    private static final String TO_EMAIL = "adam_sunhao@163.com";
    //private static final String TO_EMAIL = "752104486@qq.com";

    @Before
    public void before() {
        // 配置，一次即可
        OhMyEmail.config(SMTP_QQ(false), "752104486@qq.com", "vzmtgzbjdpddbeff");
        // 如果是企业邮箱则使用下面配置
        //OhMyEmail.config(SMTP_ENT_QQ(false), "xxx@qq.com", "*******");

        //OhMyEmail.config(SMTP_163(false),"adam_sunhao@163.com","sun1807");
    }

    //网易邮箱注意不要使用敏感词作为标题和内容
    @Test
    public void testSendText() throws SendMailException {
        for(int i = 0; i < 10 ;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OhMyEmail.subject("风闻")
                                .from("管理员")
                                .to(TO_EMAIL)
                                .text("电子书推荐")
                                .send();
                        System.out.println(new Date());
                    } catch (SendMailException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testSendHtml() throws SendMailException {
        OhMyEmail.subject("这是一封测试HTML邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 style=\"color:red\">信件内容</h1>")
                .send();
        Assert.assertTrue(true);
    }

    @Test
    public void testSendAttach() throws SendMailException {
        OhMyEmail.subject("这是一封测试附件邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .attach(new File("/Users/biezhi/Downloads/hello.jpeg"), "测试图片.jpeg")
                .send();
        Assert.assertTrue(true);
    }

    @Test
    public void testSendAttachURL() throws SendMailException, MalformedURLException {
        OhMyEmail.subject("这是一封测试网络资源作为附件的邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html("<h1 font=red>信件内容</h1>")
                .attachURL(new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556446485504&di=5784174894e248e474ad19e79c902cbb&imgtype=0&src=http%3A%2F%2Fwww.jituwang.com%2Fuploads%2Fallimg%2F140315%2F259557-14031514035264.jpg"),
                        "测试图片.jpeg")
                .send();
        Assert.assertTrue(true);
    }

    @Test
    public void testPebble() throws IOException, PebbleException, SendMailException {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("register.html");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("username", "biezhi");
        context.put("email", "admin@biezhi.me");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Pebble模板邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html(output)
                .send();
        Assert.assertTrue(true);
    }

    @Test
    public void testJetx() throws SendMailException {
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.getTemplate("/register.jetx");

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("username", "biezhi");
        context.put("email", "admin@biezhi.me");
        context.put("url", "<a href='http://biezhi.me'>https://biezhi.me/active/asdkjajdasjdkaweoi</a>");

        StringWriter writer = new StringWriter();
        template.render(context, writer);
        String output = writer.toString();
        System.out.println(output);

        OhMyEmail.subject("这是一封测试Jetx模板邮件")
                .from("小姐姐的邮箱")
                .to(TO_EMAIL)
                .html(output)
                .send();
        Assert.assertTrue(true);
    }

}