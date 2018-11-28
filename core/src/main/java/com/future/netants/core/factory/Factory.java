package com.future.netants.core.factory;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public interface Factory {

    <T> T createObject();

    <T> T createObject(String name);

    <T> T createObject(Class<T> clazz);

    <T> T newInstance(Class<T> clazz);

}
