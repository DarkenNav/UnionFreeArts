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
@Controller
public class MainController {

    Repository repository  = RepositoryFactory.getSiteRepository();;

    List<Site> list  = new ArrayList<Site>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {



        //list = getList();
        ModelAndView modelAndView = new ModelAndView();
        HSAllSites allSites = new HSAllSites();
        modelAndView.addObject("sites", repository.query(allSites));
        modelAndView.setViewName("admin/sites");
        return modelAndView;
    }

    @RequestMapping(value = "/newSite")
    public ModelAndView checkUser(@ModelAttribute("newSite") Site site){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        site.setId(1l);
        repository.add(site);
        //list.add(site);
        return modelAndView;
    }

    private List<Site> getList() {
        //List<Site> list = new ArrayList<Site>();
        list.add(new Site("http://yandex.ru"));
        list.add(new Site("http://lenta.ru"));
        list.add(new Site("http://google.ru"));
        list.add(new Site("http://gazeta.ru"));
        return list;
    }
}
