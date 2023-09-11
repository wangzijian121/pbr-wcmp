package com.zlht.pbr.algorithm.wcmp.utils;

import cn.hutool.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * @author zijian Wang
 */
public class WxBizDataCryptUtil {
    private String appId;
    private String sessionKey;

    public WxBizDataCryptUtil(String appId, String sessionKey) {
        this.appId = appId;
        this.sessionKey = sessionKey;
    }

    public JSONObject decrypt(String encryptedData, String iv) throws Exception {
        // Base64 decode
        byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
        byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData);
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        SecretKeySpec secretKey = new SecretKeySpec(sessionKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

        byte[] decryptedBytes = cipher.doFinal(encryptedDataBytes);
        String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);
        JSONObject decryptedJson = new JSONObject(decryptedData);

        if (!decryptedJson.getJSONObject("watermark").get("appid").equals(appId)) {
            throw new Exception("Invalid Buffer");
        }

        return decryptedJson;
    }

    private byte[] unpad(byte[] s) {
        int padLength = s[s.length - 1];
        return java.util.Arrays.copyOfRange(s, 0, s.length - padLength);
    }
}