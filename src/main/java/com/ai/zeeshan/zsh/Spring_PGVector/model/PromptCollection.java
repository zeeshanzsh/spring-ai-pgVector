package com.ai.zeeshan.zsh.Spring_PGVector.model;

/**
 * @author zeeshan
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PromptCollection {

    @JsonProperty("prompts")
    private List<PromptDefinition> prompts;

    public List<PromptDefinition> getPrompts() {
        return prompts;
    }

    public void setPrompts(List<PromptDefinition> prompts) {
        this.prompts = prompts;
    }
}
