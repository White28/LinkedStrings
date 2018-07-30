package com.white.graphtextsearcher.model;

import java.util.List;

public class ResponseFromGenerate {

    private String baseWord;
    private List<String> generatedWords;

    public ResponseFromGenerate(String baseWord, List<String> generatedWords) {
        this.baseWord = baseWord;
        this.generatedWords = generatedWords;
    }

    public String getBaseWord() {
        return baseWord;
    }

    public void setBaseWord(String baseWord) {
        this.baseWord = baseWord;
    }

    public List<String> getGeneratedWords() {
        return generatedWords;
    }

    public void setGeneratedWords(List<String> generatedWords) {
        this.generatedWords = generatedWords;
    }
}
