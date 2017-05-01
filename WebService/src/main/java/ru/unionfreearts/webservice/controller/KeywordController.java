package ru.unionfreearts.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.dao.specifications.hibernate.KeywordsByPersonId;
import ru.unionfreearts.webservice.model.Keyword;
import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.model.Person;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/keyword")
public class KeywordController {

    @Autowired
    private IRepository<Keyword> keywordRepository;

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getKeywords(@PathVariable Long personId) {
        List<Keyword> keywords = keywordRepository.query(new KeywordsByPersonId(personId));
        if (keywords.size() > 0) {
            return new ResponseEntity<>(keywords, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Keyword> add(@RequestBody Keyword keyword) {
        System.out.println(keyword.getId()+" "+keyword.getName()+" "+keyword.getPerson().getId());
        if (keywordRepository.add(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Keyword> update(@RequestBody Keyword keyword){
        if (keywordRepository.set(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.OK);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Keyword> remove(@RequestBody Keyword keyword){
        if (keywordRepository.remove(keyword)) {
            return new ResponseEntity<>(keyword, HttpStatus.OK);
        }
        return new ResponseEntity<>(keyword, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
