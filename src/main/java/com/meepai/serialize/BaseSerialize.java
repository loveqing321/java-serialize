package com.meepai.serialize;

import java.util.List;

/**
 * Created by meepai on 2017/7/17.
 */
public abstract class BaseSerialize<T> {

    /**
     * 序列化
     *
     * @param messages
     * @return
     */
    public abstract T serialize(List<Message> messages);

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    public abstract Object unserialize(T str);
}
