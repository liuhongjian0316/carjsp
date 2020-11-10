package work.aijiu.common.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MySocketHandler extends TextWebSocketHandler {
    private static ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(MySocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        //建立连接
        users.put(webSocketSession.getId(), webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String message = webSocketMessage.getPayload().toString();
        logger.info(webSocketSession + "---->" + webSocketMessage + ":"+ message);
        TextMessage textMess = new TextMessage(message);
        webSocketSession.sendMessage(textMess);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.info("handleTransportError=====================");
        if(users.get(webSocketSession.getId()) != null){
            users.remove(webSocketSession.getId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        //连接建立完毕
        logger.info("用户： " + webSocketSession.getRemoteAddress() + " is leaving, because:" + closeStatus);
        if(users.get(webSocketSession.getId()) != null){
            users.remove(webSocketSession.getId());
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }
}
