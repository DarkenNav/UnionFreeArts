package ru.unionfreearts.webservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Keyword;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.Repositories;
import ru.unionfreearts.webservice.repository.fake.FakeKeywords;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "api/admin/keyword")
public class KeywordController {
    private IRepository<Keyword> keywordIRepository = Repositories.getKeywordFakeRepository();

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<Keyword>> getKeywords(@PathVariable Long personId) {
        Set<Keyword> keywords = new HashSet<>();
        keywords.add(keywordIRepository.get(1));
        keywords.add(keywordIRepository.get(2));
        if (keywords != null) {
            return new ResponseEntity<>(keywords, HttpStatus.OK);
        }
        return new ResponseEntity<>(keywords, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Keyword> add(@RequestBody Keyword keyword) {
        System.out.println(keyword.getId()+" "+keyword.getName());
        if (keywordIRepository.add(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Keyword> update(@RequestBody Keyword keyword){
        if (keywordIRepository.set(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.OK);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Keyword> remove(@RequestBody Keyword keyword){
        if (keywordIRepository.remove(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.OK);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
