package work.aijiu.common.config.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Websocket
 */
@Configuration
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 添加拦截地址以及相应的websocket消息处理器
        registry.addHandler(new MySocketHandler(), "/websocket")
                .setAllowedOrigins("*");

        registry.addHandler(new MySocketHandler(), "/sockJs")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
