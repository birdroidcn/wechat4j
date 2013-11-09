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
    public String replyContent = null;
    public Dispatcher(String value,List<Rule> rules){
        
        Iterator it = rules.iterator();
        boolean flag = false;
        Rule rule = null;
        Rule defaultRule = null;
        //遍历规则
        while (it.hasNext()){
            rule = (Rule)it.next();
            String ruleValue = rule.getValue();
            //省缺rule
            if(ruleValue.equals("default")){
            	defaultRule = rule;
            } 
            if(rule.getIsRegExp()){
            	flag = value.matches(ruleValue);
            }else{
            	flag = value.equals(ruleValue);
            }
            if(flag) break;
        }
        //如果没有匹配，赋省缺值
        if(!flag && defaultRule!=null){
        	rule = defaultRule;
        	flag = true;
        }
        if(flag){
		    try{
		        Class<?> serviceClass = Class.forName(rule.getService());
		        this.serivce = (WXService)serviceClass.newInstance();
		    }catch (Exception e) {
		        e.printStackTrace();
		        replyContent = rule.getService();
		    }
        }
        

    }
    public WXService getSerivce(){
        return this.serivce;
    }

}
