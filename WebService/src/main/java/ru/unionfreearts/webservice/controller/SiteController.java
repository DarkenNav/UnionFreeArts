package ru.unionfreearts.webservice.controller;

import org.springframework.stereotype.Controller;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.repository.RepositoryFactory;
import ru.unionfreearts.webservice.responseCreator.ResponseCreator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by Михалыч on 21.04.2017.
 */
@Controller
public class SiteController {

    Repository sites = RepositoryFactory.getSiteRepository();

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") long id) {
        //Customer customer = customersDAO.getCustomer(id);
        Site site = (Site) sites.get(id);
        if (site != null) {
            return ResponseCreator.success(getHeaderVersion(), site);
        } else {
            return ResponseCreator.error(404, Error.NOT_FOUND.getCode(),
                    getHeaderVersion());
        }
    }
}
