package com.eplian.wechat.component;

import java.io.Serializable;

public class Rule implements Serializable {

	private String value;
    private String service;
    private boolean isRegExp;
    public Rule(String value,String service,boolean isRegExp){
        this.value  = value;
        this.service = service;
        this.isRegExp = isRegExp;
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
    public boolean getIsRegExp() {
        return isRegExp;
    }
    public void setIsRegExp(boolean isRegExp) {
        this.isRegExp = isRegExp;
    }
}
