package com.meepai.serialize.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.Message;
import com.meepai.serialize.MessageCreator;

import java.io.IOException;
import java.util.List;
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
    public String serialize(List<Message> messages){
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
        List ret = null;
        try {
            long start = System.nanoTime();
            ret = mapper.readValue(str, List.class);
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
