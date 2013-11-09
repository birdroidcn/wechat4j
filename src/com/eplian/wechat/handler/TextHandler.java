package com.eplian.wechat.handler;

import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.core.message.reply.RplTextMsg;
import com.eplian.wechat.handler.Handler;
import com.eplian.wechat.component.Rule;
import com.eplian.wechat.component.Dispatcher;
import com.eplian.wechat.service.WXService;
import com.eplian.wechat.service.remindSvs;
import com.eplian.wechat.service.KeepMsg;
import com.eplian.wechat.service.couponSvs;
import com.eplian.wechat.service.HelpSvs;

import com.hdty.tms.db.DatabaseOperation;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    	String info = "目前优惠券只支持实体券~\n"+
        "回复您的手机号,很快联系你~\n"+
        "如果你喜欢主动/害羞,给我电话吧xxxxxx";
    	List<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule("^[1-4]","com.eplian.wechat.service.couponSvs",true)); //数据库读出
        rules.add(new Rule("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)","com.eplian.wechat.service.remindSvs",true));
        rules.add(new Rule("0|(help)","com.eplian.wechat.service.HelpSvs",true));
        rules.add(new Rule("5",info,false));
        rules.add(new Rule("default","com.eplian.wechat.service.KeepMsg",false));
        RplMsg rplMsg=  this.dispatcher(rules);

        return rplMsg;
    }
    
}
