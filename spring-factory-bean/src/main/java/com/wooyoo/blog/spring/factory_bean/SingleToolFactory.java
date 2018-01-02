package com.wooyoo.blog.spring.factory_bean;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class SingleToolFactory extends AbstractFactoryBean<Tool> {

    private int factoryId;
    private int toolId;

    public Class<?> getObjectType() {
        return Tool.class;
    }

    protected Tool createInstance() throws Exception {
        return new Tool(toolId);
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }
}
