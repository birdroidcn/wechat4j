package com.eplian.wechat.core;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.NameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-11
 * Time: 上午10:40
 */
public class Common {
    static String grant_type = "client_credential";
    public static String getToken(String appid,String secret){
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("https://api.weixin.qq.com/cgi-bin/token");
        NameValuePair query[] = {new NameValuePair("grant_type",grant_type),
                                 new NameValuePair("appid",appid),
                                 new NameValuePair("secret",secret)};
        getMethod.setQueryString(query);
        String body = "";
        try{
            int status = httpClient.executeMethod(getMethod);
            if(status == HttpStatus.SC_OK){
                body = getMethod.getResponseBodyAsString();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            getMethod.releaseConnection();
            Gson g= new  GsonBuilder().create();
            Map<String,String> map = g.fromJson(body,new TypeToken<Map<String, String>>() {}.getType());
            return map.get("access_token");
        }
    }
}
