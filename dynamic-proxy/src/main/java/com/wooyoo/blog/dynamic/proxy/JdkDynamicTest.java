package com.wooyoo.blog.dynamic.proxy;

import java.lang.reflect.Proxy;

public class JdkDynamicTest {
    public static void main(String[] args) {
        EchoHandler echoHandler = (EchoHandler) Proxy.newProxyInstance(JdkDynamicTest.class.getClassLoader(), new Class[]{EchoHandler.class}, new JdkDynamicInvocationHandler());
        System.out.println(echoHandler.echo("hello"));
    }
}
