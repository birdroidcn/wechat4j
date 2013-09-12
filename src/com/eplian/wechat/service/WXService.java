package com.eplian.wechat.service;

import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-3
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public interface WXService {
    public RplMsg excute(RecvMsg msg);
}
