package com.tfkfan.webgame.network.pack.shared;

import com.tfkfan.webgame.network.pack.InitPack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMessagePack implements InitPack {
    private Integer messageType;
    private String message;
}
