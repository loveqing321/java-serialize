package com.meepai.serialize.json.jsonlib;

import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.Message;
import com.meepai.serialize.MessageCreator;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class JsonLibDemo extends BaseSerialize<String> {

    /**
     * 序列化
     *
     * @param messages
     * @return
     */
    public String serialize(List<Message> messages){
        long start = System.nanoTime();
        JSONArray array = JSONArray.fromObject(messages);
        String json = array.toString();
        long duration = System.nanoTime() - start;
        System.out.print("json-lib \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
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
        JSONArray array = JSONArray.fromObject(str);
        Object obj = JSONArray.toArray(array);
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        JsonLibDemo jsonLib = new JsonLibDemo();
        String json = jsonLib.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = jsonLib.unserialize(json);
//        System.out.println(obj);
        System.out.println();
    }
}
