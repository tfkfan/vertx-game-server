package com.tfkfan.webgame.network.pack.shared;

import com.tfkfan.webgame.network.pack.Pack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionPack implements Pack {
    private String message;
}
