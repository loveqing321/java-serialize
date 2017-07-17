package com.meepai.serialize;

import java.io.Serializable;

/**
 * Created by meepai on 2017/7/17.
 */
public class Message implements Serializable {

    private int id;

    private String subject;

    private String label;

    private String from;

    private String to;

    private long sendTime;

    public Message() {
    }

    public Message(int id, String subject, String label, String from, String to, long sendTime) {
        this.id = id;
        this.subject = subject;
        this.label = label;
        this.from = from;
        this.to = to;
        this.sendTime = sendTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }


}
