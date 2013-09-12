package com.eplian.wechat.core.message.reply;

/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
public class RplNewsMsgItem {
    private String title;
    private String description;
    private String picUrl;
    private String url;
    public RplNewsMsgItem(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
