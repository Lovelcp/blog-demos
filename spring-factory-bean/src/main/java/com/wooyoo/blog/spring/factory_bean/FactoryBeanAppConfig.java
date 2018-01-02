package com.wooyoo.blog.spring.factory_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig {

    @Bean(name = "tool")
    public ToolFactory toolFactory() {
        ToolFactory toolFactory = new ToolFactory();
        toolFactory.setFactoryId(7070);
        toolFactory.setToolId(2);
        return toolFactory;
    }
}
