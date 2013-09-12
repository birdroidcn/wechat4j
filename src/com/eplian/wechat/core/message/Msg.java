package com.eplian.wechat.core.message;

/**
 * Created with IntelliJ IDEA.
 * User: www
 * Date: 13-9-1
 * Time: 下午10:42
 * To change this template use File | Settings | File Templates.
 */
public class Msg {

    protected String id;
    protected String toUser;
    protected String fromUser;
    /**
     * created timestamp
     */
    protected String createDt;
    /**
     * type: text, image, location, link
     */
    protected String msgType;
    public Msg(){}
    public Msg(String toUser,String fromUser,String createDt,String msgType) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.createDt = createDt;
        this.msgType = msgType;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getToUser() {
        return toUser;
    }
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }
    public String getFromUser() {
        return fromUser;
    }
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
    public String getCreateDt() {
        return createDt;
    }
    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
