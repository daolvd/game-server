package com.livegame.game.handlermanagement;

import com.livegame.game.ws.handler.IWSHandler;
import com.livegame.game.ws.handler.impl.LoginHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WSHandlerManagement {


    public static HashMap<String, IWSHandler> mapHandler = new HashMap<>();

    public WSHandlerManagement() {
        mapHandler.put("login", new LoginHandler());
    }
}
