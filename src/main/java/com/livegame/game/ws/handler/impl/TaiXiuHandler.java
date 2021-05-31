package com.livegame.game.ws.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livegame.game.entity.Message;
import com.livegame.game.entity.response.WSLoginResponse;
import com.livegame.game.ws.handler.IWSHandler;
import com.livegame.game.ws.handler.WSHandler;
import org.yeauty.pojo.Session;

public class TaiXiuHandler extends WSHandler implements IWSHandler {

    @Override
    public void handleMessage(Session session, Message message) throws JsonProcessingException { // todo : viết hàm xử lý theo commnd

        this.sendMessage(session, new WSLoginResponse());
    }
}
