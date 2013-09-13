wechat4j
========

基于J2EE的微信公众平台开发包
功能
-----------------------------------
1.支持所有的消息接口。

2.支持自定义菜单。(Todo:通过页面直接制作菜单)

3.消息分发机制，方便集中于对消息的处理。

4.关键字分发机制。

5.Todo:支持session

使用
-----------------------------------
step1.在web.xml中配置servlet以及微信公众平台上的token
```
<servlet>
    <servlet-name>wechat4j</servlet-name>
    <servlet-class>com.eplian.wechat.core.Servlet</servlet-class>
    <init-param>
        <param-name>token</param-name>
        <param-value>wechat</param-value>
    </init-param>
</servlet>
```
step2.在handler包中，实现与消息相应的Handler的handle方法，如图片消息对应ImageHandler.java
```
public class ImageHandler extends Handler{
    public ImageHandler(RecvMsg msg){
         super(msg);
    }
    public RplMsg handle(){
        //add your business
        RplTextMsg rplTextMsg = new RplTextMsg(this.msg,"");//此处返回文字消息
        return rplTextMsg;
    }
}
```
step3.若要使用关键字功能，必须实现service的execute方法已经相应的分发规则

实现WXService接口
```
public class S1 implements WXService{
     public RplMsg excute(RecvMsg msg){
          RplTextMsg rplMsg = new RplTextMsg(msg,"hehe");
         return rplMsg;
     }
}
```
制定规则，调用父类Handler的dispatcher方法
```
public class TextHandler extends Handler{
    public TextHandler(RecvMsg msg){
        super(msg);
    }
    public RplMsg handle(){
        //关键字转发 or add your business
        List<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule("1","com.eplian.wechat.service.S1")); //数据库读出
        rules.add(new Rule("2","com.eplian.wechat.service.S2"));
        RplMsg rplMsg=  this.dispatcher(rules);

        return rplMsg;
    }
}
```
依赖的包
-----------------------------------
commons-codec-1.8.jar commons-httpclient-3.1.jar
gson-2.2.4.jar jdom-2.0.5.jar servlet-api.jar

