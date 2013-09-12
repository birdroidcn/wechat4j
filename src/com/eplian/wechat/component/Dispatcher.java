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
    public Dispatcher(RecvMsg msg,List<Rule> rules){
        String type = msg.getMsgType();
        String content =  msg.getContent();
        //遍历规则
        Iterator it = rules.iterator();
        while (it.hasNext()){
            Rule rule = (Rule)it.next();
            if(rule.getType().equals(type)){
                if(type.equals("text")
                        && !rule.getContent().equals("")
                        && !rule.getContent().equals(content))
                    continue;
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
