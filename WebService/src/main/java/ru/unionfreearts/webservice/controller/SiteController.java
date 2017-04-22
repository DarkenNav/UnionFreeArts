package ru.unionfreearts.webservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.repository.fake.FakeSites;
import ru.unionfreearts.webservice.specifications.hibernate.HSAllSites;

import java.util.List;


/**
 * Контроллер для сайтов
 * @author Dmitry Kostyukov
 */
@RestController
@RequestMapping(value = "/site")
public class SiteController {
    private Repository<Site> siteRepository = new FakeSites();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> get(@PathVariable Long id) {
        Site site = siteRepository.get(id);
        if (site != null) {
            return new ResponseEntity<Site>(site, HttpStatus.OK);
        }
        return new ResponseEntity<Site>(site, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAll() {
        List<Site> siteList = siteRepository.query(new HSAllSites());
        if (siteList != null) {
            return new ResponseEntity<List<Site>>(siteList, HttpStatus.OK);
        }
        return new ResponseEntity<List<Site>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Site> add(@RequestBody Site site) {
        System.out.println(site.getId()+" "+site.getName());
        if (siteRepository.add(site)) {
            return new ResponseEntity<Site>(site, HttpStatus.CREATED);
        }
        return new ResponseEntity<Site>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Site> update(@RequestBody Site site){
        if (siteRepository.set(site)) {
            return new ResponseEntity<Site>(site, HttpStatus.OK);
        }
        return new ResponseEntity<Site>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Site> remove(@RequestBody Site site){
        if (siteRepository.remove(site)) {
            return new ResponseEntity<Site>(site, HttpStatus.OK);
        }
        return new ResponseEntity<Site>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
