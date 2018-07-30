package com.white.graphtextsearcher.controller;

import com.white.graphtextsearcher.model.ResponseFromGenerate;
import com.white.graphtextsearcher.model.SearchResponse;
import com.white.graphtextsearcher.model.WordToGenerate;
import com.white.graphtextsearcher.service.GraphTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class GraphTextController {

    @Autowired
    private GraphTextService graphTextService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "word") String searchWord) {
        try {
            SearchResponse searchResponse = graphTextService.searchLinkedStrings(searchWord);
            return new ResponseEntity<>(searchResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody WordToGenerate word) {
        try {
            ResponseFromGenerate responseFromGenerate = graphTextService.generateLinkedStrings(word);
            return new ResponseEntity<>(responseFromGenerate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
