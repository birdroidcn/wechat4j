package com.eplian.wechat.component;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-1
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */
import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.component.Rule;
import com.eplian.wechat.service.WXService;

import java.lang.Class;
import java.util.List;
import java.util.Iterator;

public class Dispatcher {
    private WXService serivce = null;
    public Dispatcher(String value,List<Rule> rules){
        //遍历规则
        Iterator it = rules.iterator();
        while (it.hasNext()){
            Rule rule = (Rule)it.next();
            if(rule.getValue().equals(value)){
                try{
                    Class<?> serviceClass = Class.forName(rule.getService());
                    this.serivce = (WXService)serviceClass.newInstance();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
    public WXService getSerivce(){
        return this.serivce;
    }

}
