package com.qujiali.jiaogegongren.common.utils.encrypt;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author QiZai
 * @desc
 * @date 2018/5/3 15:13
 */

public final class SecurityUtil {
    private SecurityUtil() {
    }

    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIU7xo3roGUEOfhjijMUeIqbGn/mmNP0nRep6uUv\nPdg9o3Mcz7rIr1enpHvSB+W5Fr+3K81PBZQhbb6DRoyXv3UCAwEAAQ==";

    public static final String CHARSET = "UTF-8";

    /**
     * BASE64解码
     *
     * @param key
     * @return
     */
    public static final byte[] decryptBASE64(String key) {
        try {
            return new Base64().decode(key);
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
    }

    /**
     * BASE64编码
     *
     * @param key
     * @return
     */
    public static final String encryptBASE64(byte[] key) {
        try {
            return new Base64().encode(key);
        } catch (Exception e) {
            throw new RuntimeException("加密错误", e);
        }
    }

    /**
     * 公钥加密  测试使用，模拟客户端公钥加密
     *
     * @param data
     * @return
     */
    public static final String encryptRSAPublic(Object data) {
        try {
            if (data != null) {
                return encryptBASE64(RSACoder.encryptByPublicKey(data.toString().getBytes(CHARSET), decryptBASE64(PUBLIC_KEY)));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("加密错误", e);
        }
    }

    /**
     * AES密钥加密数据
     *
     * @return
     */
    public static final Map<String, String> getBase64AESData(String jsonData) {
        try {
                byte[] aesKey=RSACoder.generateAESKey(128);
                //构建SecretKeySpec，初始化Cipher对象时需要该参数
                SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
                //构建Cipher对象，需要传入一个字符串，格式必须为"algorithm/mode/padding"或者"algorithm/",意为"算法/加密模式/填充方式"
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                //初始化Cipher对象
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

                //加密数据
                byte[] resultBytes = cipher.doFinal(jsonData.getBytes());

                //结果用Base64转码
                String data =encryptBASE64(resultBytes);
                String key =encryptBASE64(RSACoder.encryptByPublicKey(aesKey, decryptBASE64(PUBLIC_KEY)));
                Map<String, String> mapResult=new HashMap<String, String>();
                mapResult.put("data",data);
                mapResult.put("key",key);
                return  mapResult;
        } catch (Exception e) {
            throw new RuntimeException("加密错误", e);
        }
    }


}
