package com.ai.zeeshan.zsh.Spring_PGVector.model;

/**
 * @author zeeshan
 */


public class PromptDefinition {

    private String name;
    private String description;
    private String template;

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}

