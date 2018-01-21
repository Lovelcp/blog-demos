package com.wooyoo.blog.spring.circular.dependency.constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ComponentScan(basePackages = "com.wooyoo.blog.spring.circular.dependency")
@ContextConfiguration(classes = ConstructorTest.class)
public class ConstructorTest {

    @Test
    public void init() {
        // exception occurred when context is loading
    }
}