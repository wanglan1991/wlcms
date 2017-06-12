package com.ekt.cms.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @version  1.0
 */
public class EncryptUtil {

    /**
     * 生成加密码
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 检查是否相等
     *
     * @param passwordCrypted
     * @param password
     * @return
     */
    public static boolean checkPassword(String passwordCrypted, String password) {
        return BCrypt.checkpw(password, passwordCrypted);
    }
}
