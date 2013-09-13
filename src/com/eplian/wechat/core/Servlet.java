package com.eplian.wechat.core;

import com.eplian.wechat.core.Validate;
import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.handler.Handler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

/**
 * Created with IntelliJ IDEA.
 * User: bird
 * Date: 13-9-1
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */
public class Servlet extends HttpServlet{
    /**
     * 微信token
     */
    private String token;
    /**
     * init config
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.token = config.getInitParameter("token");
    }

    /**
     * 微信公共平台认证接口
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String echostr = request.getParameter("echostr");
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        if (Validate.check(this.token, signature, timestamp, nonce)) {
            out.print(echostr);
        } else {
            out.print("false");
        }
        out.flush();
    }

    /**
     * 微信消息接收接口
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //validate signature
        boolean valid = Validate.check(this.token,request.getParameter("signature"),
                request.getParameter("timestamp"),
                request.getParameter("nonce"));
        if (true) {
            try {
                RecvMsg recvMsg = new RecvMsg(request.getInputStream());
                dispatcher(recvMsg, response);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            send400(response);
        }
    }

    /**
     * 处理消息
     * @param msg
     * @param response
     * @throws Exception
     */
    public void dispatcher(RecvMsg msg,HttpServletResponse response) throws Exception {
        response.setContentType("text/xml; charset=UTF-8");
        String type = msg.get("MsgType");
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        Class<?> serviceClass = Class.forName("com.eplian.wechat.handler."+type+"Handler");
        Constructor serviceCons= serviceClass.getConstructor(new Class[]{RecvMsg.class});
        Handler handler = (Handler)serviceCons.newInstance(msg);
        RplMsg  rplMsg = handler.handle();
        reply(rplMsg,response);
    }
    private void reply(RplMsg msg,HttpServletResponse response){
        try{
            OutputStream out = response.getOutputStream();
            Document doc = msg.toDocument();
            new XMLOutputter().output(doc, out);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * send 400(bad request) response
     */
    public void send400(HttpServletResponse response) {
        try {
            response.sendError(400);
        } catch (Exception ignore) {

        }
    }
}
