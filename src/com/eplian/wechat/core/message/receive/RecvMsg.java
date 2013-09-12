package com.eplian.wechat.core.message.receive;

/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午11:29
 * To change this template use File | Settings | File Templates.
 */
import com.eplian.wechat.core.message.Msg;
import com.eplian.wechat.core.message.reply.RplMsg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
public class RecvMsg extends Msg{
    /**
     * content, such as text, image url, link etc
     */
    private String content;
    private String picUrl;
    /**
     * session 保存回话信息
     */
    public Map<String,String> session=new HashMap<String,String>();

    public RecvMsg(InputStream is)throws IOException,JDOMException{
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(is);
        this.parseMsg(document);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private void parseMsg(Document document){
        Element rootElement = document.getRootElement();
        id = rootElement.getChildTextTrim("MsgId");
        toUser = rootElement.getChildTextTrim("ToUserName");
        fromUser = rootElement.getChildTextTrim("FromUserName");
        msgType = rootElement.getChildTextTrim("MsgType");
        content = rootElement.getChildTextTrim("Content");
        createDt = rootElement.getChildTextTrim("CreateTime");
        picUrl =  rootElement.getChildTextTrim("PicUrl");
        //event message
        if ("event".equals(msgType) ){
            String event = rootElement.getChildText("EventKey");
            String eventKey = rootElement.getChildText("EventKey");
            msgType = event;
            content = eventKey;
        }
    }
    /**
     * get reply message
     */
    public RplMsg buildRplMsg() {
        return new RplMsg(this.fromUser,this.toUser,
                (System.currentTimeMillis() / 1000)+"","",false);
    }
}
