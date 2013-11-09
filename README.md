wechat4j
========

基于J2EE的微信公众平台开发包
功能
-----------------------------------
1.支持所有的消息接口。

2.支持自定义菜单,**新增页面方式自定义菜单**。

3.消息分发机制，方便对不同类型消息的处理。

4.关键字分发机制，**新增正则匹配、省缺规则**。

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
step2.在com.eplian.wechat.core.handler包中，实现与消息相应Handler的handle方法，如图片消息对应ImageHandler.java
```
public class ImageHandler extends Handler{
    public ImageHandler(RecvMsg msg){
         super(msg);
    }
    public RplMsg handle(){
        //add your business
        String picUrl = this.msg.get("PicUrl");//接收到的消息参数均与开放平台一致
        RplTextMsg rplTextMsg = new RplTextMsg(this.msg,"收到");//此处返回文字消息
        return rplTextMsg;
    }
}
```
step3.若要使用关键字功能，需实现com.eplian.wechat.service.WXService的execute方法、定制分发规则

3.1实现WXService接口
```
public class S1 implements WXService{
     public RplMsg excute(RecvMsg msg){
          RplTextMsg rplMsg = new RplTextMsg(msg,"hehe");
         return rplMsg;
     }
}
```
3.2制定规则，调用父类Handler的dispatcher方法
```
public class TextHandler extends Handler{
    public TextHandler(RecvMsg msg){
        super(msg);
    }
    public RplMsg handle(){
        //关键字转发 or add your business
        List<Rule> rules = new ArrayList<Rule>();
        //Rule构造方法第一个参数为要匹配的关键字
        //第二个为要转发到的服务，如果未实现WXService接口，则直接当作字符串返回
        //第三个为是否启用正则匹配
        rules.add(new Rule("^[1-4]","com.eplian.wechat.service.S1",true));
        rules.add(new Rule("5","收到留言",false));
        rules.add(new Rule("default","com.eplian.wechat.service.S2",false));//省缺规则
        RplMsg rplMsg=  this.dispatcher(rules);

        return rplMsg;
    }
}
```
依赖的包
-----------------------------------
commons-codec-1.8.jar commons-httpclient-3.1.jar

gson-2.2.4.jar jdom-2.0.5.jar servlet-api.jar

