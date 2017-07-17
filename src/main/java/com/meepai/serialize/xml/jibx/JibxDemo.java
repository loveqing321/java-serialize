package com.meepai.serialize.xml.jibx;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;
import org.jibx.runtime.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class JibxDemo extends BaseSerialize<String> {

    public String serialize(Messages messages) {
        long start = System.nanoTime();
        String str = null;
        try {
            IBindingFactory factory = BindingDirectory.getFactory(Messages.class);
            IMarshallingContext mctx = factory.createMarshallingContext();
            mctx.setIndent(2);
            StringWriter sw = new StringWriter();
            mctx.marshalDocument(messages, "UTF-8", null, sw);
            str = sw.toString();
            sw.close();
        } catch (JiBXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("jibx \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return str;
    }

    public Object unserialize(String str) {
        long start = System.nanoTime();
        Messages messages = null;
        try {
            IBindingFactory factory = BindingDirectory.getFactory(Messages.class);
            IUnmarshallingContext ctx = factory.createUnmarshallingContext();
            messages = (Messages) ctx.unmarshalDocument(new StringReader(str));
        } catch (JiBXException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return messages;
    }

    public static void main(String[] args){
        JibxDemo jibx = new JibxDemo();
        String xml = jibx.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(xml);
        Object obj = jibx.unserialize(xml);
//        System.out.println(obj);
        System.out.println();
    }

}
