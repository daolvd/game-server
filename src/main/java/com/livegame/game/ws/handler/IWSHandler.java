package com.livegame.game.ws.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livegame.game.entity.Message;
import org.yeauty.pojo.Session;

public interface IWSHandler {

    void handleMessage(Session session, Message message) throws JsonProcessingException;

}
