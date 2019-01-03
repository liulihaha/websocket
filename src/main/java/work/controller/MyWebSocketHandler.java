package work.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import good.bean.Message;

@Component
public class MyWebSocketHandler implements WebSocketHandler{

    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生
    public static final Map<String, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<String, WebSocketSession>();
    }

    //握手实现连接后
    //仅连接的时候执行
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		String name = (String) webSocketSession.getAttributes().get("name");
		System.out.println("-----------");
        if (userSocketSessionMap.get(name) == null) {
            userSocketSessionMap.put(name, webSocketSession);
            String msg=name+"加入聊天室。。。。";
            System.out.println(msg);
            Message message2=new Message();
            message2.setMessageText("您有一条新消息");
            TextMessage message=new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message2));
            sendMessageToUser2(message);
        }
	}

	//发送信息前的处理
	//页面发送消息过来时执行
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
		System.out.println("**********");
		if(webSocketMessage.getPayloadLength()==0)return;

        //得到Socket通道中的数据并转化为Message对象
        Message msg=new Gson().fromJson(webSocketMessage.getPayload().toString(),Message.class);
        String a=msg.getMessageText();
        String bString=session.getAttributes().get("name").toString()+":"+a;
        msg.setMessageText(bString);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        msg.setMessageDate(now);
        //将信息保存至数据库
//        youandmeService.addMessage(msg.getFromId(),msg.getFromName(),msg.getToId(),msg.getMessageText(),msg.getMessageDate());

        //发送Socket信息
        sendMessageToUser(msg.getToId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
     * 在此刷新页面就相当于断开WebSocket连接,原本在静态变量userSocketSessionMap中的
     * WebSocketSession会变成关闭状态(close)，但是刷新后的第二次连接服务器创建的
     * 新WebSocketSession(open状态)又不会加入到userSocketSessionMap中,所以这样就无法发送消息
     * 因此应当在关闭连接这个切面增加去除userSocketSessionMap中当前处于close状态的WebSocketSession，
     * 让新创建的WebSocketSession(open状态)可以加入到userSocketSessionMap中
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		 System.out.println("WebSocket:"+webSocketSession.getAttributes().get("uid")+"close connection");
	        Iterator<Map.Entry<String,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
	        while(iterator.hasNext()){
	            Map.Entry<String,WebSocketSession> entry = iterator.next();
	            if(entry.getValue().getAttributes().get("name")==webSocketSession.getAttributes().get("name")){
	                userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
	                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("name") + "removed");
	            }
	        }
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//发送信息的实现
    public void sendMessageToUser(int uid, TextMessage message)
            throws IOException {
    	//给单人发送信息
//        WebSocketSession session = userSocketSessionMap.get(uid);
//        if (session != null && session.isOpen()) {
//            session.sendMessage(message);
//        }
    	for(String name:userSocketSessionMap.keySet()) {
    		WebSocketSession session = userSocketSessionMap.get(name);
            if (session != null && session.isOpen()) {
                session.sendMessage(message);
            }
    	}
    }
    
  //发送信息的实现
    public void sendMessageToUser2(TextMessage message)
            throws IOException {
    	//给多人发送信息上线
    	for(String name:userSocketSessionMap.keySet()) {
    		WebSocketSession session = userSocketSessionMap.get(name);
            if (session != null && session.isOpen()) {
                session.sendMessage(message);
            }
    	}
        
    }

}
