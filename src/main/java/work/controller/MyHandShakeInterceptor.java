package work.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import good.bean.UserBean;

/**
 * websocket握手拦截器
 * 拦截握手前，握手后的两个切面
 */
public class MyHandShakeInterceptor implements HandshakeInterceptor {
	
	public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
//        System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getSession(false).getAttribute("user") + "]已经建立连接");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            // 标记用户
            UserBean user = (UserBean) session.getAttribute("user");
            if(user!=null){
                map.put("name", user.getName());//为服务器创建WebSocketSession做准备
                System.out.println("用户名："+user.getName()+"加入聊天室");
            }else{
                System.out.println("user为空");
                return false;
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

}
