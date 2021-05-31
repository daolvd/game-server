package com.livegame.game.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.livegame.game.entity.Message;
import com.livegame.game.entity.impl.LoginRequestBody;
import com.livegame.game.handlermanagement.WSHandlerManagement;
import com.livegame.game.ws.enity.UserConnect;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint(host = "${ws.host}", port = "${ws.port}", corsOrigins = "*")
public class ServerGame {

    static HashMap<String, Session> mapUser = new HashMap<>();
    static HashMap<Session, String> mapSessionUser = new HashMap<>();
    static ArrayList<Session> sessions = new ArrayList<>();
    @Autowired
    private WSHandlerManagement wsHandlerManagement;

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam String userName, @RequestParam MultiValueMap reqMap) {

        // todo: add to hashmap
        //session.setSubprotocols("stomp");
        if (!"ok".equals(req)) {
            System.out.println("Authentication failed!");
            session.close();
        } else {
            if (mapUser.containsKey(userName)) {
                mapSessionUser.remove(mapUser.get(userName));
            }
            mapSessionUser.put(session, userName);
            mapUser.put(userName, session);
            sessions.add(session);
        }
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam String userName, @RequestParam MultiValueMap reqMap) {
        System.out.println("new connection");
        System.out.println(req);

    }

    @OnClose
    public void onClose(Session session) throws IOException {

        // remove session connect
        // mapUser.remove()
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) throws JsonProcessingException {

        log.info(mapSessionUser.keySet().toString());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Message mess = new ObjectMapper().readValue(message, Message.class);
//        LoginRequestBody loginRequestBody = null;
//        if (mess.getType().equals("login")) {
//            //loginRequestBody =  new ObjectMapper().readValue(message, (JavaType) mess.getRequestBody());
//            loginRequestBody = new ObjectMapper().convertValue(mess.getRequestBody(), LoginRequestBody.class);
//        }

        wsHandlerManagement.mapHandler.get(mess.getType()).handleMessage(session,mess);
//        System.out.println(mapSessionUser.keySet().toString());
//        for (Session userSession : mapSessionUser.keySet()) {
//            Message mess1 = new Message(mapSessionUser.get(session), message);
//            mess1.setRequestBody(loginRequestBody);
//            String json = ow.writeValueAsString(mess1);
//            userSession.sendText(json);
//        }


    }


    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }
}
