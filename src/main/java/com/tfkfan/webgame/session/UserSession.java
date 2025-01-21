package com.tfkfan.webgame.session;

import com.tfkfan.webgame.config.Constants;
import com.tfkfan.webgame.config.Fields;
import com.tfkfan.webgame.config.MessageTypes;
import com.tfkfan.webgame.game.model.players.Player;
import com.tfkfan.webgame.network.message.Message;
import com.tfkfan.webgame.network.message.MessageType;
import com.tfkfan.webgame.network.pack.shared.GameMessagePack;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserSession {
    private final String id;
    private final boolean testUser;
    private String roomVerticleId;

    private UUID roomKey;
    private Player player;

    public UserSession() {
        this.id = UUID.randomUUID().toString();
        this.testUser = false;
    }

    public UserSession(String roomVerticleId) {
        this.id = UUID.randomUUID().toString();
        this.roomVerticleId = roomVerticleId;
        this.testUser = false;
    }

    public UserSession(String sessionId, String roomVerticleId) {
        this.id = sessionId;
        this.roomVerticleId = roomVerticleId;
        this.testUser = false;
    }

    public UserSession(String sessionId, String roomVerticleId, boolean testUser) {
        this.id = sessionId;
        this.roomVerticleId = roomVerticleId;
        this.testUser = testUser;
    }

    public UserSession(String sessionId, String roomVerticleId, Player player) {
        this.id = sessionId;
        this.roomVerticleId = roomVerticleId;
        this.testUser = false;
        this.player = player;
    }

    public void sendTo(String address, JsonObject message) {
        Vertx.currentContext().owner().eventBus().publish(address,
                JsonObject.mapFrom(message));
    }

    public void send(int messageType, Object content) {
        Object data = content;
        if (!(content instanceof String) && !(content instanceof JsonObject))
            data = JsonObject.mapFrom(content);

        Vertx.currentContext().owner().eventBus().publish(Constants.WS_SESSION_CHANNEL + id,
                new JsonObject().put(Fields.type, messageType)
                        .put(Fields.data, data));
    }

    public void send(Message message) {
        Vertx.currentContext().owner().eventBus().publish(Constants.WS_SESSION_CHANNEL + id,
                JsonObject.mapFrom(message));
    }

    public void send(JsonObject message) {
        Vertx.currentContext().owner().eventBus().publish(Constants.WS_SESSION_CHANNEL + id, message);
    }

    public void sendErrorMessage(String errorMsg) {
        /* if (socket == null) return;*/
    }

    public void sendText(MessageType type, String message) {
        send(MessageTypes.MESSAGE, new GameMessagePack(type.getType(), message));
    }

    public void sendText(String message) {
        sendText(MessageType.SYSTEM, message);
    }
}
