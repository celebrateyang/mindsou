package com.mindsou.service;

import com.google.gson.GsonBuilder;
import com.mindsou.IDao.IMindDao;
import com.mindsou.entity.Message;
import com.mindsou.entity.Mind;
import com.mindsou.websocket.MyWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 竹庆 on 2016/10/18.
 */
public class MindPushService {
    private IMindDao mindDao;
    @Resource
    MyWebSocketHandler handler;

    public List<Mind> getSendList(String oneMind) {
        List<Mind> list = mindDao.listBySql("select uid,content from t_mind where content like '%"+oneMind+"%' ",null,Mind.class,true);
        return list;
    }

    public void  sendMessageWithinList(Long currentUid,String currentContent, List<Mind> sameToSendList) throws IOException {


       // List<Mind> removeCurrentList = sameToSendList;
        //给当前提交post的uid发送信息
        for (Mind mind : sameToSendList) {

            if (mind.getUid().equals(currentUid))
                continue;
            //还要加一个逻辑，将不在线的uid删除掉
            if (true)//判断mind.getUid是否在线。
            {
                //向当前提交Post的uid发送信息(currentUid)
                handler.sendMessageToUser(currentUid, createTextmessage(mind.getContent() + ",信息来自于uid:" + mind.getUid() + "</br>"));
                if(true)//判断mind.getUid是否在线。给在线的uid发送时，由于该在线的uid已经拥有之前的内容，只要将currentUid的内容发给他，即可。
                    handler.sendMessageToUser(mind.getUid(),createTextmessage(currentContent + ",信息来自于uid:" + currentUid + "</br>"));
            }
        }

    }

    protected TextMessage createTextmessage(String mindContent){
        Message msg=new Message();
        msg.setText("信息内容是:"+mindContent);

        TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
        return textMessage;
    }

    @Inject
    public void setMindDao(IMindDao mindDao) {
        this.mindDao = mindDao;
    }

    public MyWebSocketHandler getHandler() {
        return handler;
    }

    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
    }
}
