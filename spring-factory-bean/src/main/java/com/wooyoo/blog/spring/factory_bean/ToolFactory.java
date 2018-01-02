package com.wooyoo.blog.spring.factory_bean;

import org.springframework.beans.factory.FactoryBean;

public class ToolFactory implements FactoryBean<Tool> {

    private int factoryId;
    private int toolId;

    public Tool getObject() throws Exception {
        return new Tool(toolId);
    }

    public Class<?> getObjectType() {
        return Tool.class;
    }

    public boolean isSingleton() {
        return false;
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
