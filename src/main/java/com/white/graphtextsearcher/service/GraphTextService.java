package com.white.graphtextsearcher.service;

import com.white.graphtextsearcher.model.ResponseFromGenerate;
import com.white.graphtextsearcher.model.SearchResponse;
import com.white.graphtextsearcher.model.WordToGenerate;

public interface GraphTextService {

    ResponseFromGenerate generateLinkedStrings(WordToGenerate word);

    SearchResponse searchLinkedStrings(String searchWord);
}
