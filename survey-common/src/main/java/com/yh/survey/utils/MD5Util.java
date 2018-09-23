package com.yh.survey.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * MD5加密工具
 *
 * @author yanhuan
 */
public final class MD5Util {

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    private static final char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * md5加密
     * 1-执行加密后获得长度16的字节数组
     * 2-遍历字节数组，每一个字节取出高4位和低4位和15做与运算
     * 3-与运算的结果作为chars数组的下标取出字符
     * 4-将字符拼接起来作为加密后的密文
     *
     * @param source 原字符串
     * @return 加密后的字符串
     */
    public static String md5(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] targetBytes = digest.digest(source.getBytes());
            for (byte b : targetBytes) {
                int highValue = (b >> 4) & 15;
                int lowValue = b & 15;
                sb.append(chars[highValue]).append(chars[lowValue]);
            }
        } catch (Exception e) {
            logger.error("MD5Util md5 error,source:{},error:{}", source, e);
        }
        return sb.toString();
    }

}
