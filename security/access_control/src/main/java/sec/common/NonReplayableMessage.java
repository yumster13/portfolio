package sec.common;

import java.io.Serializable;

public class NonReplayableMessage extends TextMessage implements Serializable {

    private final String[] randomValue;
    private Integer clientID;

    public NonReplayableMessage(String txt, MsgType type,String... value) {
        super(txt, type);
        this.randomValue = value;
    }

    public NonReplayableMessage(String txt, MsgType type, Integer clientID, String... value) {
        super(txt, type);
        this.randomValue = value;
        this.clientID = clientID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public String[] getRandomValue() {
        return randomValue;
    }
}
