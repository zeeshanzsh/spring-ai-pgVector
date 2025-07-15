package com.ai.zeeshan.zsh.Spring_PGVector.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zeeshan
 */

@Service
public class FindInVectorData {

    private final VectorStore vectorStore;

    public FindInVectorData(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    /**
     *
     * @param query
     * @return
     */
    public String findSimilarData(String query){

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(query).withTopK(5));

        return documents
                .stream()
                .map(document -> document.getContent().toString())
                .collect(Collectors.joining());
    }
}
