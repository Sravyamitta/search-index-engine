package com.searchengine.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.searchengine.model.Document;
import com.searchengine.util.TextProcessor;

public class SearchEngine {

    private final Indexer indexer;
    private final Map<Integer, Document> documentStore = new HashMap<>();

    public SearchEngine(Indexer indexer) {
        this.indexer = indexer;
    }

    public void addDocument(Document document) {
        documentStore.put(document.getId(), document);
        indexer.index(document);
    }

    public List<Integer> search(String query) {
        Map<Integer, Integer> scores = new HashMap<>();
        List<String> queryTokens = TextProcessor.tokenize(query);

        for (String token : queryTokens) {
            for (Integer docId : indexer.getDocuments(token)) {
                scores.put(docId, scores.getOrDefault(docId, 0) + 1);
            }
        }

        return scores.entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
}
