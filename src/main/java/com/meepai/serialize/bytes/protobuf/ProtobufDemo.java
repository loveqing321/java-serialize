package com.meepai.serialize.bytes.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.meepai.serialize.MessageCreator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class ProtobufDemo {

    public byte[] serialize() {
        long start = System.nanoTime();
        MessagesProto.Messages.Builder builder = MessagesProto.Messages.newBuilder();
        MessagesProto.Message.Builder mbuilder = MessagesProto.Message.newBuilder();
        for(int i = 0; i< MessageCreator.COUNT; i++){
            mbuilder.setId(i);
            mbuilder.setSubject("subject" + i);
            mbuilder.setLabel("label" + i);
            mbuilder.setFrom("test@163.com");
            mbuilder.setTo("test2@163.com");
            mbuilder.setSendTime(new Date().getTime());
            builder.addMessage(mbuilder.build());
        }
        byte[] bytes = builder.build().toByteArray();
        long duration = System.nanoTime() - start;
        System.out.print("Protobuf \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return bytes;
    }

    public Object unserialize(byte[] data) {
        long start = System.nanoTime();
        MessagesProto.Messages messages = null;
        try {
            messages = MessagesProto.Messages.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return messages;
    }

    public static void main(String[] args){
        ProtobufDemo protobuf = new ProtobufDemo();
        byte[] bytes = protobuf.serialize();
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = protobuf.unserialize(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();
    }
}
