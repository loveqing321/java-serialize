package com.meepai.serialize;

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
    public abstract T serialize(Messages messages);

    /**
     * 反序列化
     *
     * @param str
     * @return
     */
    public abstract Object unserialize(T str);
}
