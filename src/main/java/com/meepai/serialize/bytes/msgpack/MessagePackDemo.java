package com.meepai.serialize.bytes.msgpack;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.Message;
import com.meepai.serialize.MessageCreator;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class MessagePackDemo extends BaseSerialize<byte[]> {

    private MessagePack msgpack = new MessagePack();

    public MessagePackDemo(){
        // 注册javabean的类型
        msgpack.register(Message.class);
    }

    public byte[] serialize(List<Message> messages){
        byte[] json = null;
        long start = System.nanoTime();
        try {
            json = msgpack.write(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("MessagePack \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return json;
    }

    @Override
    public Object unserialize(byte[] bytes) {
        long start = System.nanoTime();
        Object obj = null;
        try {
            obj = msgpack.read(bytes, Templates.tList(Templates.TValue));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        MessagePackDemo pack = new MessagePackDemo();
        byte[] bytes = pack.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = pack.unserialize(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();
    }
}
