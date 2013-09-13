package com.eplian.wechat.service;

import com.eplian.wechat.service.WXService;
import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplTextMsg;
import com.eplian.wechat.core.message.reply.RplMsg;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-2
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */

public class S2 implements WXService{
    public RplMsg excute(RecvMsg msg){
        RplTextMsg rplMsg = new RplTextMsg(msg,"heihei");
        return rplMsg;
    }
}
