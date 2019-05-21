package com.mindsou.controller;

/**
 * Created by 竹庆 on 2016/8/17.
 */
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mindsou.entity.Message;
import com.mindsou.entity.User;
import com.mindsou.websocket.MyWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;


import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/msg")
public class MsgController {

   @Resource
    MyWebSocketHandler handler;

    Map<Long, User> users = new HashMap<Long, User>();

    //模拟一些数据
    //本地访问时，开两个浏览器，分别输入http://localhost:8010/msg/login.do?uid=1 和 http://localhost:8010/msg/login.do?uid=2
    @ModelAttribute
    public void setReqAndRes() {
        User u1 = new User();
        u1.setId(1L);
        u1.setName("张三");
        users.put(u1.getId(), u1);

        User u2 = new User();
        u2.setId(2L);
        u2.setName("李四");
        users.put(u2.getId(), u2);

    }

    //用户登录
    @RequestMapping(value="login",method=RequestMethod.GET)
    public ModelAndView doLogin(User user,String uid,String name,HttpServletRequest request){
        System.out.println("uid===>"+uid);
        request.getSession().setAttribute("uid", uid);
        request.getSession().setAttribute("name", name);
        return new ModelAndView("redirect:talk");
    }

    //跳转到交谈聊天页面
    @RequestMapping(value="talk",method=RequestMethod.GET)
    public ModelAndView talk(){
        return new ModelAndView("talk");
    }
    //跳转到发布广播页面
    @RequestMapping(value="broadcast",method=RequestMethod.GET)
    public ModelAndView broadcast(){
        return new ModelAndView("broadcast");
    }

    //发布系统广播（群发）
    @ResponseBody
    @RequestMapping(value="broadcast",method=RequestMethod.POST)
    public void broadcast(String text) throws IOException{
        Message msg=new Message();
        msg.setDate(new Date());
        msg.setFrom(-1L);
        msg.setFromName("系统广播");
        System.out.println("111===========>"+text);
        System.out.println("222====>");
        System.out.println("333===>");
        msg.setTo(0L);
        msg.setText(text);
        TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
        handler.broadcast(textMessage);

    }

    public MyWebSocketHandler getHandler() {
        return handler;
    }

    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
    }
}

