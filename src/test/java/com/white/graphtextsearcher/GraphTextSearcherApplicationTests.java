package com.white.graphtextsearcher;

import static org.junit.Assert.assertEquals;

import com.white.graphtextsearcher.model.ResponseFromGenerate;
import com.white.graphtextsearcher.model.SearchResponse;
import com.white.graphtextsearcher.model.WordToGenerate;
import com.white.graphtextsearcher.service.GraphTextService;
import com.white.graphtextsearcher.util.PermutationUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphTextSearcherApplicationTests {

    @Mock
    private GraphTextService graphTextService;

    private String searchWord;
    private List<String> listOfLinkedStrings;
    private List<String> listOfBaseWords;
    private WordToGenerate word;
    private ResponseFromGenerate responseFromGenerate;
    private SearchResponse searchResponse;
    private PermutationUtil permutationUtil;

    @Before
    public void setUp() {
        word = new WordToGenerate();
        word.setWord("abc");
    }

    @Test
    public void shouldReturnGeneratedLinkedStringsWithBaseWordWhenLinkedStringsDoesntExist() {
        listOfLinkedStrings = Arrays.asList("bac", "cab", "acb", "bca", "cba");
        responseFromGenerate = new ResponseFromGenerate(word.getWord(), listOfLinkedStrings);

        when(graphTextService.generateLinkedStrings(word)).thenReturn(responseFromGenerate);
        ResponseFromGenerate response = graphTextService.generateLinkedStrings(word);

        assertEquals(response.getGeneratedWords(), responseFromGenerate.getGeneratedWords());
    }

    @Test
    public void shouldReturnEmptyResponseWhenLinkedStringsAlreadyExistsWithTheSameWord() {
        responseFromGenerate = new ResponseFromGenerate(null, null);

        when(graphTextService.generateLinkedStrings(word)).thenReturn(responseFromGenerate);
        ResponseFromGenerate response = graphTextService.generateLinkedStrings(word);

        assertEquals(response.getGeneratedWords(), responseFromGenerate.getGeneratedWords());
    }

    @Test
    public void shouldReturnBaseWordsWhenLinkedStringsExistsBySearchWord() {
        searchWord = "cab";

        listOfBaseWords = Arrays.asList("abc");
        searchResponse = new SearchResponse(listOfBaseWords);

        when(graphTextService.searchLinkedStrings(searchWord)).thenReturn(searchResponse);
        SearchResponse response = graphTextService.searchLinkedStrings(searchWord);

        assertEquals(response.getBaseWords(), searchResponse.getBaseWords());
    }

    @Test
    public void shouldReturnEmptyListOfBaseWordsWhenLinkedStringsDoesntExistBySearchWord() {
        searchResponse = new SearchResponse(new ArrayList<>());

        when(graphTextService.searchLinkedStrings(searchWord)).thenReturn(searchResponse);
        SearchResponse response = graphTextService.searchLinkedStrings(searchWord);

        assertEquals(response.getBaseWords(), searchResponse.getBaseWords());
    }

    @Test
    public void shouldReturnAllPossiblePermutations() {
        permutationUtil = new PermutationUtil(word.getWord());

        List<String> permutations = permutationUtil.getPermutations();

        assertEquals(permutations.size(), 6);
    }
}
