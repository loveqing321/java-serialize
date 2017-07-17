package com.meepai.serialize;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by meepai on 2017/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="messages")
public class Messages implements Serializable {

    public List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
