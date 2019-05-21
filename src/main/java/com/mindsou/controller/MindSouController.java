package com.mindsou.controller;

import com.google.gson.GsonBuilder;
import com.mindsou.IDao.IMindDao;
import com.mindsou.dao.MindDao;
import com.mindsou.entity.Message;
import com.mindsou.entity.Mind;
import com.mindsou.service.MindPushService;
import com.mindsou.websocket.MyWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by 竹庆 on 2016/8/4.
 */
@Controller
@RequestMapping("/")
public class MindSouController {
    @Resource
    MyWebSocketHandler handler;
    private IMindDao mindDao;

    public IMindDao getMindDao() {
        return mindDao;
    }

    @Resource
    MindPushService mindPushService;

    @Inject
    public void setMindDao(IMindDao mindDao) {
        this.mindDao = mindDao;
    }

    Map<String,String> mapUidContent = new HashMap<String,String>();
    Long i=6L;
    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpServletRequest request,Model model){
        i++;
        String uid = i.toString();
        request.getSession().setAttribute("uid", uid);
        model.addAttribute("uid",uid);
       return "first";
    }

    @RequestMapping(value = "/mindPost", method = RequestMethod.POST)
    @ResponseBody
    public String mindPost(String mindContent,String uid, HttpServletRequest request,Model model) throws IOException {
        System.out.println("uid==>"+uid);
        List<Long> uidsNeedToSend = new ArrayList<Long>();
        Iterator<Map.Entry<String, String>> iterator = mapUidContent.entrySet().iterator();
        while (iterator.hasNext()){
         Map.Entry entry = iterator.next();
//            System.out.println("uidInMindPost===>"+entry.getKey());
//            System.out.println("valueInMindPost==>"+entry.getValue());
            String oldUid = entry.getKey().toString();
            String oldContent = entry.getValue().toString();
            if(mindContent!=null && mindContent.equals(oldContent)){
                uidsNeedToSend.add(Long.valueOf(oldUid));
            }
        }
        mapUidContent.put(uid,mindContent);
        Mind mind = new Mind(Long.valueOf(uid),mindContent);
        Object[] oj = new Object[]{uid};
        List<Object> mindfromdao=mindDao.findBySql("select * from t_mind where uid = ?",oj,Mind.class,true).getDatas();
        for (Object o : mindfromdao) {
            System.out.println("o.getClass()===>"+o.getClass());
        }
        System.out.println("mindfromdao.size()===>"+mindfromdao.size());
        if(mindfromdao.size()>0)
        mindDao.update(mind);
        else
        mindDao.add(mind);

       List<Mind> sameToSendList =  mindPushService.getSendList(mindContent);

        mindPushService.sendMessageWithinList(Long.valueOf(uid),mindContent,sameToSendList);
        /*Message msg=new Message();
        msg.setText(mindContent);

        TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
        for (Long uidNeedToSend :uidsNeedToSend) {
            handler.sendMessageToUser(uidNeedToSend,textMessage);
        }*/

        //要写这么个东西，后台有一个job,不断地从redis数据库里面读<uid,String>,发现有相符的内容，则发送相符的内容给这个uid.
        //所以前面要把用户的输入数据存到redis数据库里
        return "bambooyang";

    }

    @RequestMapping(value = "boot",method = RequestMethod.GET)
    public String bootStrap(HttpServletRequest request,Model model){

        return "bootstrap";
    }


    @RequestMapping("getAnswer")
    public String getAnswer(){
        return "";
    }

    public MyWebSocketHandler getHandler() {
        return handler;
    }

    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
    }
}
