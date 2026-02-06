package com.searchengine.engine;

import com.searchengine.model.Document;
import com.searchengine.util.TextProcessor;

import java.util.*;

public class Indexer {

    // term -> set of document IDs
    private final Map<String, Set<Integer>> invertedIndex = new HashMap<>();

    public void index(Document document) {
        List<String> tokens = TextProcessor.tokenize(document.getContent());

        for (String token : tokens) {
            invertedIndex
                .computeIfAbsent(token, k -> new HashSet<>())
                .add(document.getId());
        }
    }

    public Set<Integer> getDocuments(String term) {
        return invertedIndex.getOrDefault(
            term.toLowerCase(),
            Collections.emptySet()
        );
    }

    public int getVocabularySize() {
        return invertedIndex.size();
    }
}
