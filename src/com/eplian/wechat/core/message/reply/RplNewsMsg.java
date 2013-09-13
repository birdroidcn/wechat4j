package com.eplian.wechat.core.message.reply;

import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.core.message.reply.RplNewsMsgItem;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.LinkedList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
public class RplNewsMsg extends RplMsg{
    private List<RplNewsMsgItem> items = new LinkedList<RplNewsMsgItem>();

    public RplNewsMsg(RecvMsg msg) {
        super(msg);
        setMsgType("news");
    }
    public void setItems(List<RplNewsMsgItem> items) {
        this.items = items;
    }

    public RplNewsMsg addItem(String title,String description,String picUrl,String url) {
        if(items.size() >= 10) {
            throw new IllegalArgumentException("只能接受最多10个item...");
        }
        items.add(new RplNewsMsgItem(title,description,picUrl,url));
        return this;
    }

    @Override
    public Document toDocument() {
        Document doc = super.toDocument();
        Element root = doc.getRootElement();
        createElement(root, "ArticleCount", String.valueOf(items.size()));
        Element articles = createElement(root, "Articles","");
        for(RplNewsMsgItem item : items) {
            Element i = createElement(articles, "item", "");
            createElement(i, "Title", item.getTitle());
            createElement(i, "Description", item.getDescription());
            createElement(i, "PicUrl", item.getPicUrl());
            createElement(i, "Url", item.getUrl());
        }
        return doc;
    }
}
