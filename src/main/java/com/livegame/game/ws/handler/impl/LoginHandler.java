package com.livegame.game.ws.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livegame.game.bean.MapperUtils;
import com.livegame.game.entity.Message;
import com.livegame.game.entity.impl.LoginRequestBody;
import com.livegame.game.entity.response.WSLoginResponse;
import com.livegame.game.ws.handler.IWSHandler;
import com.livegame.game.ws.handler.WSHandler;
import org.yeauty.pojo.Session;

public class LoginHandler extends WSHandler implements IWSHandler {


    @Override
    public void handleMessage(Session session, Message message) throws JsonProcessingException {
        LoginRequestBody loginRequestBody = MapperUtils.mapper.convertValue(message.getRequestBody(), LoginRequestBody.class);
        session.sendText("helllo");
        this.sendMessage(session, new WSLoginResponse());
    }
}
