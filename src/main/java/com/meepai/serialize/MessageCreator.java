package com.meepai.serialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meepai on 2017/7/17.
 */
public abstract class MessageCreator {

    public static final int COUNT = 100000;

    /**
     *
     * @return
     */
    public static Message createMessage(){
        Message message = new Message();
        message.setId(-1);
        message.setSubject("subject");
        message.setLabel("label");
        message.setFrom("test@163.com");
        message.setTo("test2@163.com");
        message.setSendTime(new Date().getTime());
        return message;
    }

    /**
     *
     * @return
     */
    public static List<Message> createMessages(){
        List<Message> messages = new ArrayList<Message>();
        Message message;
        for(int i=0; i<COUNT; i++){
            message = new Message();
            message.setId(i);
            message.setSubject("subject" + i);
            message.setLabel("label" + i);
            message.setFrom("test@163.com");
            message.setTo("test2@163.com");
            message.setSendTime(new Date().getTime());
            messages.add(message);
        }
        return messages;
    }
}
