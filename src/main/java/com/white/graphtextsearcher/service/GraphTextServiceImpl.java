package com.white.graphtextsearcher.service;

import com.white.graphtextsearcher.model.ResponseFromGenerate;
import com.white.graphtextsearcher.model.SearchResponse;
import com.white.graphtextsearcher.model.WordToGenerate;
import com.white.graphtextsearcher.util.PermutationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GraphTextServiceImpl implements GraphTextService {

    private Map<String, List<String>> mapOfLinkedStrings = new HashMap<>();

    @Override
    public ResponseFromGenerate generateLinkedStrings(WordToGenerate word) {

        if (!mapOfLinkedStrings.containsKey(word.getWord())) {
            PermutationUtil generator = new PermutationUtil(word.getWord());
            List<String> permutations = generator.getPermutations();
            permutations.remove(word.getWord());

            mapOfLinkedStrings.put(word.getWord(), permutations);

            return new ResponseFromGenerate(word.getWord(), permutations);
        } else {
            return new ResponseFromGenerate(null, null);
        }
    }

    @Override
    public SearchResponse searchLinkedStrings(String searchWord) {

        List<String> listOfBaseWords = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : mapOfLinkedStrings.entrySet()) {
            if (entry.getValue().contains(searchWord)) {
                listOfBaseWords.add(entry.getKey());
            }
        }
        return new SearchResponse(listOfBaseWords);
    }
}
