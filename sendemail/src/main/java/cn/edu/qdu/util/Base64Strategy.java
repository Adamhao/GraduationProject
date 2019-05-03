package cn.edu.qdu.util;

import java.util.Base64;

/**
 * Base64算法基于64个基本字符，加密后的string中只包含这64个字符
 * Created by Adam on 2019/4/30 17:56.
 */
public class Base64Strategy {

    /**
     * 加密
     *
     * @param src 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode(String src) {
        if (src == null) {
            throw new NullPointerException();
        }
        byte[] encodeBytes = Base64.getEncoder().encode(src.getBytes());
        return new String(encodeBytes);
    }

    /**
     * 解密
     *
     * @param src 需要解密的字符串
     * @return 解密后的字符串
     */
    public static String decode(String src) {
        if (src == null) {
            throw new NullPointerException();
        }
        byte[] decodeBytes = Base64.getDecoder().decode(src.getBytes());
        return new String(decodeBytes);
    }
}
