package com.healing.tjx.cms.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjx
 * @Description 微信小程序登陆
 * @Date 2021/6/10 9:28 下午
 */
public class WxLoginUtil {

    /**
     * 解析code 获取openid 和 sessionkey
     *
     * @param code
     * @return
     */
    public static JSONObject getOpenIdAndSessionKey(String code, String appId
            , String secret, RestTemplate restTemplate) {
        Map<String, Object> map = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
        map.put("appid", appId);
        map.put("secret", secret);
        map.put("code", code);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
        return JSONUtil.parseObj(responseEntity.getBody());
    }


    /**
     * 微信小程序手机登陆，解析获取手机号码
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public static JSONObject getPhoneNumber(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.getDecoder().decode(iv);
        try {
            // 如果密钥不足16位，那么就补足
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONUtil.parseObj(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
