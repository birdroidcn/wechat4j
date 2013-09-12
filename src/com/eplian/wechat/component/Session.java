package com.eplian.wechat.component;

import java.util.concurrent.ConcurrentHashMap;
//import com.danga.MemCached.MemCachedClient;
/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-5
 * Time: 下午8:58
 */
public class Session {
    private String id;
    //private MemCachedClient mcc = (MemCachedClient)SpringContextHolder.getBean("memcachedClient");
    public Session(String id){
        this.id = id;
    }
    /*public String get(String key){
        this.
        return  String.valueOf();
    }*/
    public void set(String key,String value){

    }
}
