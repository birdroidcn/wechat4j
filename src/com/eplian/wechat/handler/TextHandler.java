package com.eplian.wechat.handler;

import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.core.message.reply.RplTextMsg;
import com.eplian.wechat.handler.Handler;
import com.eplian.wechat.component.Rule;
import com.eplian.wechat.component.Dispatcher;
import com.eplian.wechat.service.WXService;

import java.util.List;
import java.util.ArrayList;
/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-12
 * Time: 下午10:05
 */
public class TextHandler extends Handler{
    public TextHandler(RecvMsg msg){
        super(msg);
    }
    public RplMsg handle(){
        //关键字转发 or add your business
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule("1","com.eplian.wechat.service.S1")); //数据库读出
        rules.add(new Rule("2","com.eplian.wechat.service.S2"));
        RplMsg rplMsg=  this.dispatcher(rules);

        return rplMsg;
    }
}
