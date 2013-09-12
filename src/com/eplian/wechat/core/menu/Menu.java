package com.eplian.wechat.core.menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.annotations.Expose;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.HttpMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.eplian.wechat.core.menu.Button;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-11
 * Time: 下午2:54
 */
public class Menu {
    private String token;
    @Expose
    private ArrayList<Button> button;
    public Menu(String token){
         this.token = token;
         button=new ArrayList<Button>();
    }

    /**
     * 添加按钮
     * @param btn
     * @return
     */
    public Menu add(Button btn){
         button.add(btn);
         return this;
    }

    public boolean create(){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "text/html;charset=UTF-8");

        GsonBuilder builder = new GsonBuilder();
        //不转换没有@Expose注解的字段
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String json = gson.toJson(this);

        postMethod.setRequestBody(json);
        String body = requestTemplate(postMethod);
        return resultTemplate(body);
    }

    public boolean delete(){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token;
        GetMethod getMethod = new GetMethod(url);
        String body = requestTemplate(getMethod);
        return resultTemplate(body);
    }

    public String query(){
        String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
        GetMethod getMethod = new GetMethod(url);
        return requestTemplate(getMethod);
    }

    private String  requestTemplate(HttpMethod method){
        HttpClient httpClient = new HttpClient();
        String body = "";
        try{
            httpClient.executeMethod(method);
            body = method.getResponseBodyAsString();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            method.releaseConnection();
            return  body;
        }
    }

    private boolean resultTemplate(String json){
        Gson g= new GsonBuilder().create();
        Map<String,String> map = g.fromJson(json,new TypeToken<Map<String, String>>() {}.getType());
        String errcode = map.get("errcode");
        if(errcode.equals("0")){
            return true;
        }else{
            return false;
        }
    }
}
