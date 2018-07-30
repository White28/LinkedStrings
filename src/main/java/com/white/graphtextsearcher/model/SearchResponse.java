package com.white.graphtextsearcher.model;

import java.util.List;

public class SearchResponse {

    private List<String> baseWords;

    public SearchResponse(List<String> baseWords) {
        this.baseWords = baseWords;
    }

    public List<String> getBaseWords() {
        return baseWords;
    }

    public void setBaseWords(List<String> baseWords) {
        this.baseWords = baseWords;
    }
}
