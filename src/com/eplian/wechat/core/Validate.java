package com.eplian.wechat.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午10:45
 * To change this template use File | Settings | File Templates.
 */
public class Validate {
    private static final char[] LETTERS = "0123456789ABCDEF".toCharArray();
    public static boolean check(String token,String signature,String timestamp,String nonce) {
        List<String> ss = new ArrayList<String>();
        if(null == timestamp || null == nonce || null == signature) return false;
        ss.add(timestamp);
        ss.add(nonce);
        ss.add(token);

        Collections.sort(ss);

        StringBuilder builder = new StringBuilder();
        for(String s : ss) {
            builder.append(s);
        }
        return signature.equalsIgnoreCase(sha1(builder.toString()));
    }
    private static String sha1(String value) {
        try {
            return hash(MessageDigest.getInstance("SHA1"), value);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String hash(MessageDigest digest,String src) {
        return toHexString(digest.digest(src.getBytes()));
    }

    private static String toHexString(byte[] bytes) {
        char[] values = new char[bytes.length * 2];
        int i=0;
        for(byte b : bytes) {
            values[i++] = LETTERS[((b & 0xF0) >>> 4)];
            values[i++] = LETTERS[b & 0xF];
        }
        return String.valueOf(values);
    }
}