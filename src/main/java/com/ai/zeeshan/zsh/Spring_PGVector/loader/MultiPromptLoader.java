package com.ai.zeeshan.zsh.Spring_PGVector.loader;

import com.ai.zeeshan.zsh.Spring_PGVector.model.PromptCollection;
import com.ai.zeeshan.zsh.Spring_PGVector.model.PromptDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zeeshan
 */

@Component
public class MultiPromptLoader {
    private final Map<String, PromptDefinition> promptMap;

    public MultiPromptLoader(@Value("classpath:prompts/constitution-prompt.json") Resource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PromptCollection collection = mapper.readValue(resource.getInputStream(), PromptCollection.class);

        this.promptMap = collection.getPrompts().stream()
                .collect(Collectors.toMap(PromptDefinition::getName, p -> p));
    }

    public String getTemplateByName(String name) {
        PromptDefinition prompt = promptMap.get(name);
        if (prompt == null) {
            throw new IllegalArgumentException("Prompt not found: " + name);
        }
        return prompt.getTemplate();
    }

    public String getDescriptionByName(String name) {
        PromptDefinition prompt = promptMap.get(name);
        if (prompt == null) {
            throw new IllegalArgumentException("Prompt not found: " + name);
        }
        return prompt.getDescription();
    }
}
