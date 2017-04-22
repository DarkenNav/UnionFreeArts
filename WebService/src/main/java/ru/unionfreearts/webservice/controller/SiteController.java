package ru.unionfreearts.webservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.IRepository;
import ru.unionfreearts.webservice.repository.Repositories;
import ru.unionfreearts.webservice.repository.specifications.hibernate.HSAllSites;

import java.util.List;


/**
 * Контроллер для сайтов
 * @author Dmitry Kostyukov
 */
@RestController
@RequestMapping(value = "api/admin/site")
public class SiteController {
    private IRepository<Site> siteIRepository = Repositories.getSiteFakeRepository();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Site> get(@PathVariable Long id) {
        Site site = siteIRepository.get(id);
        if (site != null) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Site>> getAll() {
        List<Site> siteList = siteIRepository.query(new HSAllSites());
        if (siteList != null) {
            return new ResponseEntity<>(siteList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Site> add(@RequestBody Site site) {
        System.out.println(site.getId()+" "+site.getName());
        if (siteIRepository.add(site)) {
            return new ResponseEntity<>(site, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Site> update(@RequestBody Site site){
        if (siteIRepository.set(site)) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Site> remove(@RequestBody Site site){
        if (siteIRepository.remove(site)) {
            return new ResponseEntity<>(site, HttpStatus.OK);
        }
        return new ResponseEntity<>(site, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
