package com.eplian.wechat.core.message.reply;

import com.eplian.wechat.core.message.receive.RecvMsg;
/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午11:00
 * To change this template use File | Settings | File Templates.
 */
import com.eplian.wechat.core.message.Msg;
import org.jdom2.Document;
import org.jdom2.Element;

public class RplMsg extends Msg{
    // FuncFlag 位0x0001被标志时，星标刚收到的消息。
    private boolean star;

    public RplMsg(String toUser,String fromUser,String createDt,String msgType,boolean star) {
        super(toUser, fromUser, createDt, msgType);
        this.star = star;
    }

    public RplMsg(RecvMsg msg) {
        this(msg.get("FromUserName"),msg.get("ToUserName"),
                (System.currentTimeMillis() / 1000)+"","",false);
    }

    public boolean isStar() {
        return star;
    }
    public void setStar(boolean star) {
        this.star = star;
    }


    public Document toDocument() {
        Document doc = new Document();
        Element root = new Element("xml");
        doc.setRootElement(root);

        createElement(root,"ToUserName", getToUser());
        createElement(root,"FromUserName", getFromUser());
        createElement(root,"CreateTime", getCreateDt());
        createElement(root,"MsgType", getMsgType());
        createElement(root,"FuncFlag", isStar()?"1":"0");

        return doc;
    }

    @SuppressWarnings("unchecked")
    protected Element createElement(Element parent,String name,String value) {
        Element elem = new Element(name);
        elem.setText(value);
        parent.getChildren().add(elem);
        return elem;
    }
}
