package com.wooyoo.blog.spring.factory_bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:factorybean-abstract-spring.xml")
public class AbstractFactoryBeanTest {

    @Resource(name = "singleTool")
    private Tool tool1;

    @Resource(name = "singleTool")
    private Tool tool2;

    @Resource(name = "nonSingleTool")
    private Tool tool3;

    @Resource(name = "nonSingleTool")
    private Tool tool4;

    @Test
    public void testSingleToolFactory() {
        assertTrue(tool1 == tool2);
    }

    @Test
    public void testNonSingleToolFactory() {
        assertTrue(tool3 != tool4);
    }
}
