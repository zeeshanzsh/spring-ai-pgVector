package com.ai.zeeshan.zsh.Spring_PGVector.Controller;

import com.ai.zeeshan.zsh.Spring_PGVector.loader.MultiPromptLoader;
import com.ai.zeeshan.zsh.Spring_PGVector.service.FindInVectorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zeeshan
 */

@RestController
@Slf4j
public class ICSController {



    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    private final FindInVectorData findInVectorData;

    private final MultiPromptLoader promptLoader;

    public ICSController(ChatClient.Builder builder, VectorStore vectorStore, FindInVectorData findInVectorData, MultiPromptLoader promptLoader) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.findInVectorData = findInVectorData;
        this.promptLoader = promptLoader;
    }


    private String prompt = """
            Your task is to answer the questions about Indian Constitution. Use the information from the DOCUMENTS
            section to provide accurate answers. If unsure or if the answer isn't found in the DOCUMENTS section, 
            simply state that you don't know the answer.
                        
            QUESTION:
            {input}
                        
            DOCUMENTS:
            {documents}
                        
            """;

    @GetMapping("/v1/askIC")
    public String simplyICS(@RequestBody String content){

        log.info("Calling ASK IC Controller");
        PromptTemplate template =
                new PromptTemplate(prompt);

        Map<String, Object> promptParam = new HashMap<>();

        promptParam.put("input", content);
        promptParam.put("documents", findInVectorData.findSimilarData(content));

        return chatClient
                .prompt(template.create(promptParam))
                .call()
                .content();
    }

    /**
     * This controller load prompt from template JSON file
     * @param payload
     * @return
     */
    @PostMapping("/v2/ask")
    public Map<String, Object> askQuestion(@RequestBody Map<String, String> payload) {

        String promptName = payload.get("promptName");
        String input = payload.get("input");

        String templateStr = promptLoader.getTemplateByName(promptName);

        PromptTemplate template = new PromptTemplate(templateStr);

        Map<String, Object> variables = new HashMap<>();
        variables.put("input", input);

        if (promptName.equals("IndianConstitutionPrompt") || promptName.equals("GeneralKnowledgePrompt")) {
            variables.put("documents", findInVectorData.findSimilarData(input));
        } else if (promptName.equals("SimpleSummaryPrompt")) {
            variables.put("text", input);
        }

        String response= chatClient
                .prompt(template.create(variables))
                .call()
                .content();

        Map<String, Object> output = new HashMap<>();
        output.put("question", input);
        output.put("response", response);
        return output;
    }

}
