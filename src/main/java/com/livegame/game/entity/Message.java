package com.livegame.game.entity;

import com.livegame.game.entity.impl.LoginRequestBody;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends MessageBase {
    String userName;
    String message;
    Object requestBody;

    public Message(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }
}
