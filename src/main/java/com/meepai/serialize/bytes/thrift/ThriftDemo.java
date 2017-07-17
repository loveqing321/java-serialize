package com.meepai.serialize.bytes.thrift;

import com.meepai.serialize.MessageCreator;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransportException;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class ThriftDemo {

    /**
     *
     * @return
     */
    public byte[] serializeCompactProtocol(){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(32);
        TProtocol protocol = new TCompactProtocol(buffer);
        return serialize(buffer, protocol);
    }

    /**
     *
     * @return
     */
    public byte[] serializeBinaryProtocol(){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(32);
        TProtocol protocol = new TBinaryProtocol(buffer);
        return serialize(buffer, protocol);
    }

    /**
     *
     * @return
     */
    public byte[] serializeJSONProtocol(){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(32);
        TProtocol protocol = new TJSONProtocol(buffer);
        return serialize(buffer, protocol);
    }

    /**
     *
     * @return
     */
    public byte[] serializeSimpleJSONProtocol(){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(32);
        TProtocol protocol = new TSimpleJSONProtocol(buffer);
        return serialize(buffer, protocol);
    }

    /**
     * 以指定协议来序列化
     *
     * @param protocol
     * @return
     */
    public byte[] serialize(TMemoryBuffer buffer, TProtocol protocol){
        long start = System.nanoTime();
        Messages messages = new Messages();
        Message message;
        for(int i = 0; i< MessageCreator.COUNT; i++){
            message = new Message();
            message.setId(i);
            message.setSubject("subject" + i);
            message.setLabel("label" + i);
            message.setFrom("test@163.com");
            message.setTo("test2@163.com");
            message.setSendTime(new Date().getTime());
            messages.addToMessages(message);
        }
        try {
            messages.write(protocol);
        } catch (TException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("Thrift " + getProtocolName(protocol) + "\t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return buffer.getArray();
    }

    /**
     *
     * @param bytes
     * @return
     */
    public Object unserializeCompactProtocol(byte[] bytes){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(bytes.length);
        try {
            buffer.readAll(bytes, 0, bytes.length);
        } catch (TTransportException e) {
        }
        return unserialize(new TCompactProtocol(buffer));
    }

    /**
     *
     * @param bytes
     * @return
     */
    public Object unserializeBinaryProtocol(byte[] bytes){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(bytes.length);
        try {
            buffer.readAll(bytes, 0, bytes.length);
        } catch (TTransportException e) {
        }
        return unserialize(new TBinaryProtocol(buffer));
    }

    /**
     *
     * @param bytes
     * @return
     */
    public Object unserializeJSONProtocol(byte[] bytes){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(bytes.length);
        try {
            buffer.readAll(bytes, 0, bytes.length);
        } catch (TTransportException e) {
        }
        return unserialize(new TJSONProtocol(buffer));
    }

    /**
     *
     * @param bytes
     * @return
     */
    public Object unserializeSimpleJSONProtocol(byte[] bytes){
        // 内存级缓冲，默认32长度，自动增加
        TMemoryBuffer buffer = new TMemoryBuffer(bytes.length);
        try {
            buffer.readAll(bytes, 0, bytes.length);
        } catch (TTransportException e) {
        }
        return unserialize(new TSimpleJSONProtocol(buffer));
    }

    /**
     *
     * @param protocol
     * @return
     */
    public Object unserialize(TProtocol protocol){
        long start = System.nanoTime();
        Messages messages = new Messages();
        try {
            messages.write(protocol);
        } catch (TException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return messages;
    }

    /**
     *
     * @param protocol
     * @return
     */
    public String getProtocolName(TProtocol protocol){
        if(protocol instanceof TCompactProtocol){
            return "compact protocol";
        } else if(protocol instanceof TBinaryProtocol){
            return "binary protocol";
        } else if(protocol instanceof TSimpleJSONProtocol){
            return "simple json protocol";
        } else if(protocol instanceof TJSONProtocol){
            return "json protocol";
        }
        return null;
    }

    public static void main(String[] args){
        ThriftDemo thrift = new ThriftDemo();
        byte[] bytes = thrift.serializeCompactProtocol();
        System.out.print("\t\t");
        Object obj = thrift.unserializeCompactProtocol(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();

        bytes = thrift.serializeBinaryProtocol();
        System.out.print("\t\t");
        obj = thrift.unserializeBinaryProtocol(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();

        bytes = thrift.serializeJSONProtocol();
        System.out.print("\t\t");
        obj = thrift.unserializeJSONProtocol(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();

        bytes = thrift.serializeSimpleJSONProtocol();
        System.out.print("\t\t");
        obj = thrift.unserializeSimpleJSONProtocol(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();
    }
}
