package com.meepai.serialize.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.meepai.serialize.BaseSerialize;
import com.meepai.serialize.Message;
import com.meepai.serialize.MessageCreator;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by meepai on 2017/7/17.
 */
public class FastjsonDemo extends BaseSerialize<String> {

    /**
     * 序列化
     *
     * @param messages
     * @return
     */
    public String serialize(List<Message> messages){
        long start = System.nanoTime();
        String json = JSONObject.toJSONString(messages);
        long duration = System.nanoTime() - start;
        System.out.print("fastjson \t serialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
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
        List obj = JSONObject.parseArray(str);
        long duration = System.nanoTime() - start;
        System.out.print("unserialize: " + duration + "(" + TimeUnit.NANOSECONDS.toMillis(duration) + "ms)");
        return obj;
    }

    public static void main(String[] args){
        FastjsonDemo fast = new FastjsonDemo();
        String json = fast.serialize(MessageCreator.createMessages());
        System.out.print("\t\t");
//        System.out.println(json);
        Object obj = fast.unserialize(json);
        System.out.println();
    }
}
