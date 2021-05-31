package com.livegame.game.entity.impl;

import com.livegame.game.entity.IWSRequestBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody implements IWSRequestBody {
    String username;
    String pass;


}
