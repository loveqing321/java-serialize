package com.meepai.serialize;

import com.meepai.serialize.bytes.jdk.JDKDemo;
import com.meepai.serialize.bytes.marshalling.MarshallingDemo;
import com.meepai.serialize.bytes.msgpack.MessagePackDemo;
import com.meepai.serialize.bytes.protobuf.ProtobufDemo;
import com.meepai.serialize.bytes.thrift.ThriftDemo;
import com.meepai.serialize.json.fastjson.FastjsonDemo;
import com.meepai.serialize.json.gson.GsonDemo;
import com.meepai.serialize.json.jackson.JacksonDemo;
import com.meepai.serialize.json.jsonlib.JsonLibDemo;

/**
 * Created by meepai on 2017/7/17.
 */
public class MainTest {

    public static void main(String[] args){
        System.out.println("****************** test byte serialize/unserialize ****************");
        JDKDemo.main(args);
        MessagePackDemo.main(args);
        MarshallingDemo.main(args);
        ProtobufDemo.main(args);
        ThriftDemo.main(args);

        System.out.println();
        System.out.println("****************** test json serialize/unserialize ****************");
        JacksonDemo.main(args);
        GsonDemo.main(args);
//        JsonLibDemo.main(args);
        FastjsonDemo.main(args);

        System.out.println();
        System.out.println("****************** test xml serialize/unserialize ****************");
    }
}
