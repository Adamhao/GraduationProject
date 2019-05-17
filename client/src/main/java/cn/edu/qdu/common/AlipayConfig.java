package cn.edu.qdu.common;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    public static String return_url="http:localhost:8081/pay/success";
    public static String notify_url="http:localhost:8081/pay/notify";
    public static String gatewayUrl="https://openapi.alipaydev.com/gateway.do";
    public static String app_id="2016092300575689";
    public static String merchant_private_key="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDZiUDpLMBfcP+ZG3QcXnisEhMKRPjVRmsiDgU+uj+Iwmq4+zX4GOPeRVhRkVEGfWvh8y3L24agyG0v0QbTBS9U5T8oorr69VaJkyq+gLR6tNnDPjqdRiD2U9lXmQ5uQzmYsUnSQxuUPhzzGNJ6MMHWDOGh8kdPPkl6HVxFt7wPRmYVKtpu61Uiw6/ar6dxG3jUGjHAyej/+gzfAv97B1+oE7P0wBT7hguJlaV+f8HHMHT/pdFn1gmlW6IVGviVXI1DN8noxMf9fxowqX7cNFktTYJDsQMhxgfdF5lCEiRldtDRXo9ZP7kHe55Bop7WJg3qV7UQfLDVI3QnbsEYm6MbAgMBAAECggEBAMnZZMS4RKyKwnDEG6IzOs+XWdl+lFH5rHEGB3HkCg4RMEwCtVpHZjEZsVDGgSN/f3pCVi1BvpM0RrRGxYlK11dz/H3tOYih27F+P/xSmqT9aM6U0Hic2PsYZKS2ncBMWoZHg1M5974/QEeFsc+1rDa87ySBFg/+oNlRowwB59tW5maHjxp3Pnv6hsz22Y+ggFi00MDzzaK8CFxDzzeNfKAKP3o1cBn1BDZ4tuaUvjkIMuFJDVF8DXlvYqMWHURz+OjohZfnpY8Y+0GnhtDF+ciRtXosdeCF6GZ5prAwIO8x94MTXHzyaRl60WCdOSz6/KdOlSy3NCG1lZAT36K+eRkCgYEA73qcFIZkwPH/6faZ4N2Rx+73KvYXJHi8d7wUPM02ax2gb3yuzXtIEvFrUigIsiP0D+IYd14ii03Vv7e4UwMo/y7XavR3AlwGLb/DMD4QMwUdVCQhRL10AvbofNIPwlmoVcZPdM6NDMZLpuoPqFyg7XnLl79BT3jZ92j1fR+soEcCgYEA6Isd0GLkx+6IT56oulp2K6E/3Z33m4TJpBrpjzNQNiKZoP1CtphQIZurDNb8inmt92TxDrTDbGbrLvnvVepuzlAMTL/ew0QUplQz3JLudabiGfnRJ6LVA8PeuZWjD48CLYMES3qgH9wExkvoAmREuRsDJ/30/mKU9F/VBucnxI0CgYEAm0f2cryeEhoY5Z0YNB2XjHuf20SMxwJeuojC2pX55kTkpcXEFssMdfQCsdfxo4j6lV32nVf1sDw9ED5Rj8opyCWYWuq/TFFdVEbWiq6DlIGJfeibhdYOpmlxYJZYz52RMGURWl0gSps9rLJyiqndU365C+FguOZU9z8ghwyFw7sCgYBRTToqPjkgkYwE0kLJjJEeJe1PTUhhjiZ1gpc/4r/bfXNa7tDhSBVT+rnt7kgzHeGtyhPhgV+FyeHV9bowvpro9OGn/7khae8STAQpzREj/ymzzTzL78kr3NJE7kUpTmWDhff9b60GFoVw0t8V3vqMAR60X7zFJ7BOVCcFl9G2jQKBgDEWQM8wPFZFqA/5JXJyA88OwylLD/zOQ/fb/DXFLkEzwnNr2oe8FpkG3WEGlck7QLet0SpW/zbI3t1L5kiocPul7BUXuT/mJXsQvgbJYGJ4cIrBLCowNBNWcMma1gbKafplV22x7nmKvWMzIFaVkZtF3ZnAZ73fz7GJhHiRtJ5o";
    public static String charset="utf-8";
    public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3CRzvOgPl3CcHmKo5ZxeVsO46fZ/rqoP3IAMnsgZBtyp+lgnv8SVxWiM6q78boiraU+sUEYsxSpACoPvM3QKh2VHiEuPMEse/klV1+S6WW399cZ1jo2LDG7hkCkgP8yqyGnngZSOsg/BFdqx94tortyzQQFb/1fRb/1bXXsH2AQTiN2qiWA1d4zT2E+6yNfrK4AcrGDCGzNtfaK9jU73Xhfu9AoIyHfg3Z/tbD44eMyYkKxcmg1Q9ev1r/0NxVVo5dP0rSjqkzDqCKpb2pv4x3pjQrNcZu3fLtmAhMHI2UMr3aH2Lf0P4fhlXNll4hXdd+3CMDRCSnSLgkP2a/qJAwIDAQAB";
    public static String sign_type="RSA2";
    public static String log_path = "D:/logs/";

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
