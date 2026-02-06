package com.searchengine.util;

import java.util.Arrays;
import java.util.List;

public class Textprocessor {

    public static List<String> tokenize(String text) {
        return Arrays.asList(
            text.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .split("\\s+")
        );
    }
}
