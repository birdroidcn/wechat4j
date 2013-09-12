package com.eplian.wechat.core.menu;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-11
 * Time: 下午4:20
 */
public class Button {
    @Expose
    private String type;
    @Expose
    private String url;
    @Expose
    private String name;
    @Expose
    private String key;
    @Expose
    private ArrayList<Button> sub_button;
    /**
     * 用于创建按钮的构造方法
     * @param type
     * @param name
     * @param value
     */
    public Button(String type,String name,String value){
        this.type = type;
        this.name = name;
        if(type.equals("view")){
            this.url = value;
        }
        if(type.equals("click")){
            this.key = value;
        }
    }
    /**
     * 用于创建子菜单的构造方法
     * @param name
     */
    public Button(String name){
        this.name = name;
        sub_button = new ArrayList<Button>();
    }
    /**
     * 添加子菜单，支持链式调用
     * @param btn
     * @return
     */
    public Button add(Button btn){
        sub_button.add(btn);
        return  this;
    };
}
