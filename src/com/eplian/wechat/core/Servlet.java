package com.eplian.wechat.core;

import com.eplian.wechat.core.Validate;
import com.eplian.wechat.core.message.receive.RecvMsg;
import com.eplian.wechat.core.message.reply.RplMsg;
import com.eplian.wechat.component.Dispatcher;
import com.eplian.wechat.service.WXService;
import com.eplian.wechat.component.Rule;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        if (valid) {
            try {
                RecvMsg recvMsg = new RecvMsg(request.getInputStream());
                List<Rule> rules = new ArrayList<Rule>();
                rules.add(new Rule("text","","com.eplian.wechat.service.IsNumeber"));
                doMessage(recvMsg, rules, response);
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
     * @param rules
     * @param response
     * @throws Exception
     */
    public void doMessage(RecvMsg msg,List<Rule> rules ,HttpServletResponse response) throws Exception {
        response.setContentType("text/xml; charset=UTF-8");
        Dispatcher dispatcher = new Dispatcher(msg,rules);
        WXService ws = dispatcher.getSerivce();
        RplMsg  rplMsg = ws.excute(msg);
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
