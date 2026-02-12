
// this file starts calculating the term frequency for each document 

package com.searchengine.engine;

import java.util.HashMap;
import java.util.Map;

public class InvertedIndex {

    // term → { docId → termFrequency }
    private final Map<String, Map<Integer, Integer>> index = new HashMap<>();

    public void addDocument(int docId, String content) {

        String[] tokens = content.toLowerCase().split("\\W+");

        for (String token : tokens) {

            if (token.isBlank()) continue;

            index.putIfAbsent(token, new HashMap<>());

            Map<Integer, Integer> posting = index.get(token);

            posting.put(docId, posting.getOrDefault(docId, 0) + 1);
        }
    }

    public Map<Integer, Integer> search(String term) {
        return index.getOrDefault(term.toLowerCase(), new HashMap<>());
    }

    public Map<String, Map<Integer, Integer>> getIndex() {
        return index;
    }
}
