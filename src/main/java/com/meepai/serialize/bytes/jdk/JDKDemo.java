package com.meepai.serialize.bytes.jdk;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class JDKDemo extends BaseSerialize<byte[]> {

    public byte[] serialize(Messages messages){
        long start = System.nanoTime();

        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("Jdk \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return baos.toByteArray();
    }

    @Override
    public Object unserialize(byte[] bytes) {
        long start = System.nanoTime();
        Object obj = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        JDKDemo jdk = new JDKDemo();
        byte[] bytes = jdk.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = jdk.unserialize(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();
    }

}
