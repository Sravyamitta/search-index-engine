package com.searchengine.engine;

import com.searchengine.model.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {

    @Test
    void singleTermSearch_returnsMatchingDocuments() {
        Indexer indexer = new Indexer();
        SearchEngine searchEngine = new SearchEngine(indexer);

        searchEngine.addDocument(new Document(1, "Java is fast"));
        searchEngine.addDocument(new Document(2, "Python is fast"));

        List<Integer> results = searchEngine.search("java");

        assertEquals(1, results.size());
        assertEquals(1, results.get(0));
    }

    @Test
    void multiTermSearch_ranksByTermFrequency() {
        Indexer indexer = new Indexer();
        SearchEngine searchEngine = new SearchEngine(indexer);

        searchEngine.addDocument(new Document(1, "java java fast"));
        searchEngine.addDocument(new Document(2, "java fast"));

        List<Integer> results = searchEngine.search("java fast");

        // Doc 1: java(2) + fast(1) = 3
        // Doc 2: java(1) + fast(1) = 2
        assertEquals(List.of(1, 2), results);
    }

    @Test
    void searchWithNoMatches_returnsEmptyList() {
        Indexer indexer = new Indexer();
        SearchEngine searchEngine = new SearchEngine(indexer);

        searchEngine.addDocument(new Document(1, "java is fast"));

        List<Integer> results = searchEngine.search("golang");

        assertTrue(results.isEmpty());
    }
}
