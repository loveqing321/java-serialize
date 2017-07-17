package com.meepai.serialize.xml.beans;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * 这种序列化不适合我们日常开发的时候使用
 *
 * Created by meepai on 2017/7/17.
 */
public class BeansDemo extends BaseSerialize<String> {

    public String serialize(Messages messages) {
        long start = System.nanoTime();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.writeObject(messages);
        encoder.flush();
        long duration = System.nanoTime() - start;
        System.out.print("java beans \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        System.out.println(baos.size());
        return baos.toString();
    }

    public Object unserialize(String str) {
        long start = System.nanoTime();
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        XMLDecoder decoder = new XMLDecoder(bais);
        Object obj = decoder.readObject();
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        BeansDemo beans = new BeansDemo();
        String xml = beans.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
        System.out.println(xml);
//        Object obj = beans.unserialize(xml);
//        System.out.println(obj);
        System.out.println();
    }

}
