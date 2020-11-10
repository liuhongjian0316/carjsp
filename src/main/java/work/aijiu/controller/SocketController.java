package work.aijiu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import work.aijiu.common.config.websocket.MySocketHandler;

import javax.servlet.http.HttpSession;

@RestController
public class SocketController {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private MySocketHandler socketHandler;

    @ResponseBody
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String sendMessage() throws Exception {
        boolean 你好 = socketHandler.sendMessageToAllUsers(new TextMessage("你好"));
        return "message";
    }

}
