package com.eplian.wechat.component;

import java.io.Serializable;

public class Rule implements Serializable {

	private String type;
	private String content;
    private String service;
    public Rule(String type,String content,String service){
        this.type = type;
        this.content = content;
        this.service = service;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
