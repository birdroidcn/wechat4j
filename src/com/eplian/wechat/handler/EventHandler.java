package com.eplian.wechat.handler;

import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.core.message.reply.RplTextMsg;
import com.eplian.wechat.handler.Handler;
import sun.org.mozilla.javascript.internal.ast.NewExpression;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-12
 * Time: 下午10:24
 */
public class EventHandler extends Handler{
    public EventHandler(RecvMsg msg){
        super(msg);
    }
    public RplMsg handle(){
        //add your business
        RplTextMsg rplTextMsg = new RplTextMsg(this.msg,"");
        return rplTextMsg;
    }
}
