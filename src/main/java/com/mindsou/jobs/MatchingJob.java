package com.mindsou.jobs;

import com.google.gson.GsonBuilder;
import com.mindsou.IDao.IMindDao;
import com.mindsou.dao.MindDao;
import com.mindsou.entity.Message;
import com.mindsou.entity.Mind;
import com.mindsou.websocket.MyWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 竹庆 on 2016/9/20.
 */
@Component("taskJob")
public class MatchingJob {
    private IMindDao mindDao;
    @Resource
    MyWebSocketHandler handler;
    //@Scheduled(cron = "0/10 * * * * ?")//每10秒(每年，每月，每日，每时，每分钟内间隔10秒)   如果这样写10 * * * * ? ，其实间隔仍然是1分钟，表示每一分钟的的第2秒，注意区别。
    public void job() throws IOException {
        System.out.println("===>job is running!");
        List<Mind> list = mindDao.listBySql("select * from t_mind",null,Mind.class,true);
       //组装需要发送到客户端的清单


        for (Mind mind : list) {
            System.out.println("uid===>"+mind.getUid()+"content===>"+mind.getContent());
//            list.remove(mind);
        }
        List<Map<Long,String>> sameToSendList = new ArrayList<Map<Long,String>>();
        if(list.size()>0) {
            for (int i = 0; i < list.size(); i++) {

                Map<Long,String> haveTheSameContentMap = new HashMap<Long,String>();
                String iContent = list.get(i).getContent();
                Long iId = list.get(i).getUid();
                haveTheSameContentMap.put(iId,iContent);
                for (int j = i + 1; j < list.size(); j++) {
                    String jContent = list.get(j).getContent();
                    Long jId = list.get(j).getUid();
                    if(iContent.equals(jContent)) {
                        haveTheSameContentMap.put(jId,jContent);
                        list.remove(j);
                        j--;
                    }
                }
                if(haveTheSameContentMap.size()>1)
                sameToSendList.add(haveTheSameContentMap);
            }
        }

        //根据组装好的清单，向客户端发送信息

            for (Map<Long, String> longStringMap : sameToSendList) {

                //将具有相同mindContent的map转换为list:==>名称为mapToList,方便操作。
                List<Long> mapToList = new ArrayList<Long>();
                for (Map.Entry<Long, String> longStringEntry : longStringMap.entrySet()) {
                    Long uid =longStringEntry.getKey();
                    mapToList.add(uid);
                }
                //对mapToList进行遍历并发送
                if(mapToList.size()>0) {
                    for (int m = 0; m < mapToList.size(); m++) {
                        //对m所代表的uid发送信息，创建出除m以外的uid list：==>名称为excludeMUidList
                        List<Long> excludeMUidList = new ArrayList<Long>();
                        excludeMUidList.addAll(mapToList);//将mapToList 拷贝一份到excludeMUidList
                        excludeMUidList.remove(m);//排除m
                        //向m发送其余uid的mind
                        for (Long aLong : excludeMUidList) {
                            handler.sendMessageToUser(mapToList.get(m),createTextmessage(longStringMap.get(aLong)+",信息来自于uid:"+aLong+"</br>"));
                        }

                    }
                }


                //handler.sendMessageToUser(uid,createTextmessage(longStringEntry.getValue()+"<==来自于==>"+uid));

            }


    }

    protected TextMessage createTextmessage(String mindContent){
        Message msg=new Message();
        msg.setText("信息内容是:"+mindContent);

        TextMessage textMessage = new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg));
        return textMessage;
    }

    public IMindDao getMindDao() {
        return mindDao;
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
