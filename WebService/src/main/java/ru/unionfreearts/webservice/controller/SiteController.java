package ru.unionfreearts.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.model.Site;
import ru.unionfreearts.webservice.dao.IRepository;
import ru.unionfreearts.webservice.dao.specifications.hibernate.AllSites;

import java.util.List;


/**
 * Контроллер для сайтов
 * @author Dmitry Kostyukov
 */
@RestController
@RequestMapping(value = "/site")
public class SiteController {

    @Autowired
    private IRepository<Site> siteRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> get(@PathVariable Long id) {
        Site site = siteRepository.get(id);
        if (site != null) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAll() {
        List<Site> siteList = siteRepository.query(new AllSites());
        if (siteList != null) {
            return new ResponseEntity<>(siteList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Site> add(@RequestBody Site site) {
        if (siteRepository.add(site)) {
            return new ResponseEntity<>(site, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Site> update(@RequestBody Site site){
        if (siteRepository.set(site)) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Site> remove(@RequestBody Site site){
        if (siteRepository.remove(site)) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
