package com.wooyoo.blog.spring.bean_parser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        User user = context.getBean("testbean", User.class);
        System.out.println(user.getUserName() + "," + user.getEmail());
    }
}
