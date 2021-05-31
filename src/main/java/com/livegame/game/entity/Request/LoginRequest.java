package com.livegame.game.entity.Request;

import com.livegame.game.entity.MessageBase;
import com.livegame.game.entity.impl.LoginRequestBody;

public class LoginRequest extends MessageBase {
    String userName;
    String message;
    LoginRequestBody requestBody;

    public LoginRequest(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }
}
