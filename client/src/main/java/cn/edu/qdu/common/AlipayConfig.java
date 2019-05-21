package cn.edu.qdu.common;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {


    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092900624414";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCz9UqdOnfUcJ50ukNei/8FF/VKLbcaRIfOml6CTnWzq/3PC32H2rA8QCZhPmZYSOPwESQBm8j4j4vzGdGTrRDMSdWJ+UMFJsMigr0ui/GT6pJbil7lBoll2ZuXOadQ1SFyM9NLc2pJS+b3+y9C1ue1a+hFBjmoT/PCY67dOItkv5JssJAUk02sNnerkC4bPBCxKxLHhEgc9zDs715s+IAD2lcsUULk2Wn34cBPto61izfFOfQDvDQ9FNJ0tHgL41MmUB4nIJa1jhlEIFuh0zngSlG4A5n1GdFvSPycDFf7ZYtvnJi9O68HSwegLMnd3Eiy+JwkJU9WDo1f36uOBvwdAgMBAAECggEBAJo5PGG0qt9YSKQWwQe1CK/v0VpqBUKwuoaYCyi3Xu3FFzYqTcFw7JomC/5IwOMmTJn/xpDOVpSWSMTlOpPJvNym5idhhEtU5eSckM7kRfuTCy0CT8MNMBEmRc57LWX70bcSrSE7XzpDn0SkcuNgU6FKQfttk1oocuoEDecuv19t0gi8prktD5XhXEmafspuSr3eEOABdvkZFB6dVdslwRIPgg5VVyqGERFZfRf0Mv3a1OE1YEyyZgjA0Uk3r18yOoi1bB7bIEObxzdsUErZypVvH0a3XI4HCUknelPcxCxDf7ImboYi4vtnbesxQ7fWPgoI4zE0Aex7Hq/J5J5u08ECgYEA4fbqjodJOgF5aI2PV3OC///bXwTVreDv16rlZbmVJz5OaMGpo+7MYxSj4pQj8AYYvyxl2JWSZVk7jawbB0bHIVOKdEKYhHeUkd+LaFx0LiXQTKayzH5VDs6cToIZ43nTyLsHtHZJXXfprqEfnhj76YuauHTJCA5+JUKzyPMXExECgYEAy+Dg9cCFslAbhdbtROsFeYafOzglCaEhIwc+RbqM61W/+65d2PLYdhwsT69qS6XAytm3DVmvg4OC8vn572oci/xCXLX5mfGscwk0iUYi+oCyPCNbm75XFGgIii/1DZFyRis2SZEjyThLE2blP3Rfjdll4Od6RifzGavUX9IyQE0CgYAIaM8L5N8FyJ5DFNmb0JG0g0ofvCdTpEZoyVRmN8TkQ/5ZCbQNhMqYeWEsAoVf3mo3WyijKdXb7bk4qYoL+Eda5ygE8aGJdIXT8/TVOnOccA3TfHS1BZKWzXultFPQUm1eUA+WWLycPOU2eYqasKVXaLmmWbpNkXSCYHweY2WdcQKBgDivyBzMcOAJYf2uqr4U0qrdgXyHudSZlosUwn3ExtXQO9cQvktKCmGcp71oOpfZ+GbubfUqUKCyekBwvMuSp2lMtG7usf3Zsmcx0gexFpysF+KcuiXtoGyuGGHDv9EL9cgIz8lYr6iNHGLCo08nXkIkhi0ob1j+Vpt2qVATM6GpAoGAN9kfU4BtS8PkZjviYrGp0ohrxua70KdYX1M1tvVpYpGsVUkyRhvurSkfIeASP0PoPH6KMcGIjZdma4q7UMP1SoTTJ+CkJ+pX4T6kG2qQPDUy5S9DIpg58DLNGBfr+IruJauIC5+YgsvqLpm6Y6P0edKEmDTzT4DXc55gAoJqxwE=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs/VKnTp31HCedLpDXov/BRf1Si23GkSHzppegk51s6v9zwt9h9qwPEAmYT5mWEjj8BEkAZvI+I+L8xnRk60QzEnViflDBSbDIoK9Lovxk+qSW4pe5QaJZdmblzmnUNUhcjPTS3NqSUvm9/svQtbntWvoRQY5qE/zwmOu3TiLZL+SbLCQFJNNrDZ3q5AuGzwQsSsSx4RIHPcw7O9ebPiAA9pXLFFC5Nlp9+HAT7aOtYs3xTn0A7w0PRTSdLR4C+NTJlAeJyCWtY4ZRCBbodM54EpRuAOZ9RnRb0j8nAxX+2WLb5yYvTuvB0sHoCzJ3dxIsvicJCVPVg6NX9+rjgb8HQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://39.108.213.225:18082/pay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1:8080/pay/success";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_"
                    + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
