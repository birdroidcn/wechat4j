package com.eplian.wechat.core.message.reply;

import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.core.message.receive.RecvMsg;
import org.jdom2.Document;


/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午11:01
 * To change this template use File | Settings | File Templates.
 */
public class RplTextMsg extends RplMsg{
    private String content;

    public RplTextMsg(RecvMsg msg,String content) {
        super(msg);
        setMsgType("text");
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Document toDocument() {
        Document doc = super.toDocument();
        createElement(doc.getRootElement(), "Content", getContent());
        return doc;
    }
}
