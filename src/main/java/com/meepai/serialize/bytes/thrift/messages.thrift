namespace java com.meepai.serialize.bytes.thrift

struct Message {

    1: optional i32 id = 0;

    2: optional string subject;

    3: optional string label;

    4: optional string from;

    5: optional string to;

    6: optional i64 sendTime;

}

struct Messages {

    1: list<Message> messages;
}