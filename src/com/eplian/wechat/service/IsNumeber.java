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

public class IsNumeber implements WXService{
     public RplMsg excute(RecvMsg msg){
          String content = msg.getContent();
          String result = check(content)?"是数字":"不是数字";
          RplTextMsg rplMsg = new RplTextMsg(msg.buildRplMsg(),result);
         return rplMsg;
     }
    private   boolean check(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }
}
