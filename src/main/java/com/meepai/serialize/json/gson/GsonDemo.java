package com.meepai.serialize.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.Message;
import com.meepai.serialize.MessageCreator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class GsonDemo extends BaseSerialize<String> {

    /**
     * 序列化
     *
     * @param messages
     * @return
     */
    public String serialize(List<Message> messages){
        long start = System.nanoTime();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(messages);
        long duration = System.nanoTime() - start;
        System.out.print("gson \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return json;
    }

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    public Object unserialize(String str){
        long start = System.nanoTime();
        Gson gson = new GsonBuilder().create();
        List obj = gson.fromJson(str, List.class);
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        GsonDemo gson = new GsonDemo();
        String json = gson.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = gson.unserialize(json);
        System.out.println();
    }
}
