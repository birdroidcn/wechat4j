package com.eplian.wechat.component;

import java.io.Serializable;

public class Rule implements Serializable {

	private String value;
    private String service;
    public Rule(String value,String service){
        this.value  = value;
        this.service = service;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
}
