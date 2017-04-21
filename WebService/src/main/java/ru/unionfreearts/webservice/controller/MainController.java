package ru.unionfreearts.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.repository.RepositoryFactory;
import ru.unionfreearts.webservice.specifications.hibernate.HSAllSites;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dolgov on 17.04.2017.
 */
//@Controller
public class MainController {

    Repository repository  = RepositoryFactory.getSitesRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        HSAllSites allSites = new HSAllSites();
        modelAndView.addObject("sites", repository.query(allSites));
        modelAndView.setViewName("admin/sites");
        return modelAndView;
    }

    @RequestMapping(value = "/newSite", method = RequestMethod.POST)
    public ModelAndView addNewSite(@ModelAttribute("newSite") Site site){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        site.setId(1l);
        repository.add(site);
        return modelAndView;
    }

    @RequestMapping(value = "/editSite", method = RequestMethod.POST)
    public ModelAndView editSite(@ModelAttribute("editSite") Site site){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @RequestMapping(value = "/removeSite", method = RequestMethod.DELETE)
    public ModelAndView removeSite(@ModelAttribute("removeSite") Site site){
        ModelAndView modelAndView = new ModelAndView();
        site.setId(1l);
        repository.remove(site);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
