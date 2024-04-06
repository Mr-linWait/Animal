package com.hellen.base.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityUtil {


    /**
     * 使用随机盐值和SHA-256哈希算法加密密码
     *
     * @param password 待加密的原始密码字符串
     * @return 包含盐值和加密后的SHA-256摘要值的字符串，格式为：盐值+":"+加密后的十六进制字符串
     */
    public static String encryptPasswordWithSaltAndSHA256(String password) {
        try {
            // 生成随机盐值
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16]; // 通常盐值长度为16或更多字节
            random.nextBytes(salt);

            // 创建SHA-256消息摘要对象
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 将密码和盐值合并并转换为字节数组
            byte[] inputBytes = (password + new String(salt, StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8);

            // 计算SHA-256散列值
            byte[] hashBytes = md.digest(inputBytes);

            // 将盐值和二进制散列值分别转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            sb.append(toHex(salt));
            sb.append(':');
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            // 返回包含盐值和加密后的SHA-256摘要值的字符串
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    // 将字节数组转换为十六进制字符串
    private static String toHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    /**
     * 比较密码是否相同
     * @param originalPassword 用户当前输入的密码
     * @param saltedHashedPassword 用户存在数据库的密码
     * @return
     */
    public static boolean validatePassword(String originalPassword, String saltedHashedPassword) {
        if (saltedHashedPassword.equals(encryptPasswordWithSaltAndSHA256(originalPassword)))
            return true;
        else
            return false;
    }
}
