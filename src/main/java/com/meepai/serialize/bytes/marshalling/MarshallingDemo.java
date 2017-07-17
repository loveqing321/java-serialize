package com.meepai.serialize.bytes.marshalling;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;
import org.jboss.marshalling.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class MarshallingDemo extends BaseSerialize<byte[]> {

    private Marshaller marshaller;

    private Unmarshaller unmarshaller;

    public MarshallingDemo(){
        // 获取序列化编组工厂
        MarshallerFactory factory =  Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        try {
            marshaller = factory.createMarshaller(configuration);
            unmarshaller = factory.createUnmarshaller(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] serialize(Messages messages){
        long start = System.nanoTime();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            marshaller.start(Marshalling.createByteOutput(baos));
            marshaller.writeObject(messages);
            marshaller.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.print("Marshalling \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return baos.toByteArray();
    }

    @Override
    public Object unserialize(byte[] bytes) {
        long start = System.nanoTime();
        Object obj = null;
        try {
            unmarshaller.start(Marshalling.createByteInput(ByteBuffer.wrap(bytes)));
            obj = unmarshaller.readObject();
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
        MarshallingDemo marshalling = new MarshallingDemo();
        byte[] bytes = marshalling.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = marshalling.unserialize(bytes);
//        System.out.println(obj);
        System.out.print("\t\tbyteSize: " + bytes.length);
        System.out.println();
    }
}
