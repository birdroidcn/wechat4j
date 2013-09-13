package com.eplian.wechat.handler;

import com.eplian.wechat.component.Dispatcher;
import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.service.WXService;
import com.eplian.wechat.component.Rule;

import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-12
 * Time: 下午10:05
 */
public abstract class Handler {
    RecvMsg msg;
    public Handler(RecvMsg msg){
        this.msg = msg;
    }
    public abstract RplMsg handle();
    protected  RplMsg dispatcher(List<Rule> rules){
        Dispatcher dispatcher = new Dispatcher(msg.get("Content"),rules);
        WXService ws = dispatcher.getSerivce();
        RplMsg rplMsg= ws.excute(msg);
        return rplMsg;
    }
}
