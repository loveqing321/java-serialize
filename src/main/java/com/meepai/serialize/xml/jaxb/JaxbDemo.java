package com.meepai.serialize.xml.jaxb;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

/**
 *  Created by meepai on 2017/7/17.
 */
public class JaxbDemo extends BaseSerialize<String> {

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    public JaxbDemo(){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Messages.class);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String serialize(Messages messages) {
        long start = System.nanoTime();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            marshaller.marshal(messages, baos);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("jaxb beans \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return baos.toString();
    }

    public Object unserialize(String str) {
        long start = System.nanoTime();
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        Object obj = null;
        try {
            obj = unmarshaller.unmarshal(bais);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        JaxbDemo jaxb = new JaxbDemo();
        String xml = jaxb.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(xml);
        Object obj = jaxb.unserialize(xml);
//        System.out.println(((Messages)obj).getMessages().size());
        System.out.println();
    }

}
