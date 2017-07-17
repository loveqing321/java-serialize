package com.meepai.serialize.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.MessageCreator;
import com.meepai.serialize.Messages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class JacksonDemo extends BaseSerialize<String> {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 序列化
     *
     * @param messages
     * @return
     */
    public String serialize(Messages messages){
        String str = null;
        try {
            long start = System.nanoTime();
            str = mapper.writeValueAsString(messages);
            long duration = System.nanoTime() - start;
            System.out.print("jackson \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    public Object unserialize(String str){
        Messages ret = null;
        try {
            long start = System.nanoTime();
            ret = mapper.readValue(str, Messages.class);
            long duration = System.nanoTime() - start;
            System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args){
        JacksonDemo jackson = new JacksonDemo();
        String json = jackson.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = jackson.unserialize(json);
//        System.out.println(obj);
        System.out.println();
    }
}
