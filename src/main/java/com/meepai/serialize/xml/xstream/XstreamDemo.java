package com.meepai.serialize.xml.xstream;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class XStreamDemo extends BaseSerialize<String> {

    public String serialize(Messages messages) {
        long start = System.nanoTime();
        XStream xStream = new XStream(new DomDriver());
        String str = xStream.toXML(messages);
        long duration = System.nanoTime() - start;
        System.out.print("xstream \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return str;
    }

    public Object unserialize(String str) {
        long start = System.nanoTime();
        XStream xStream = new XStream(new DomDriver());
        Messages messages = (Messages) xStream.fromXML(str);
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return messages;
    }

    public static void main(String[] args){
        XStreamDemo xStream = new XStreamDemo();
        String xml = xStream.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(xml);
        Object obj = xStream.unserialize(xml);
//        System.out.println(obj);
        System.out.println();
    }

}
