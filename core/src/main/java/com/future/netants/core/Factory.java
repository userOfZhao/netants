package com.future.netants.core;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public interface Factory {

    <T> T createObject();

    <T> T createObject(String name);

}
