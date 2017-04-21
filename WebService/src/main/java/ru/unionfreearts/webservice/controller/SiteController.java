package ru.unionfreearts.webservice.controller;

import org.springframework.stereotype.Controller;
import ru.unionfreearts.webservice.entity.Site;
import ru.unionfreearts.webservice.repository.Repository;
import ru.unionfreearts.webservice.repository.RepositoryFactory;
import ru.unionfreearts.webservice.responseCreator.Error;
import ru.unionfreearts.webservice.responseCreator.ResponseCreator;
import ru.unionfreearts.webservice.specifications.hibernate.HSAllSites;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

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
    public Response getSite(@PathParam("id") long id) {
        //Customer customer = customersDAO.getCustomer(id);
        Site site = (Site) sites.get(id);
        if (site != null) {
            return ResponseCreator.success(getHeaderVersion(), site);
        } else {
            return ResponseCreator.error(404, Error.NOT_FOUND.getCode(),
                    getHeaderVersion());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeSite(@PathParam("id") long id) {
        sites.remove(id);
        return ResponseCreator.success(getHeaderVersion(), "removed");
//        if (sites.remove(id)) {
//            return ResponseCreator.success(getHeaderVersion(), "removed");
//        } else {
//            return ResponseCreator.success(getHeaderVersion(), "no such id");
//        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSite(Site site) {
        System.out.println("POST");
        sites.add(site);
        if (site != null) {
            return ResponseCreator.success(getHeaderVersion(), site);
        } else {
            return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
                    getHeaderVersion());
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSite(Site site) {
        sites.set(site);
        if (site != null) {
            return ResponseCreator.success(getHeaderVersion(), site);
        } else {
            return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
                    getHeaderVersion());
        }
    }

    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getSites(@QueryParam("keyword") String keyword,
                                 @QueryParam("orderby") String orderBy,
                                 @QueryParam("order") String order,
                                 @QueryParam("pagenum") Integer pageNum,
                                 @QueryParam("pagesize") Integer pageSize) {
        HSAllSites allSites = new HSAllSites();
        List<Site> listSites = sites.query(allSites);
        if (listSites != null) {
            GenericEntity<List<Site>> entity = new GenericEntity<List<Site>>(
                    listSites) {
            };
            return ResponseCreator.success(getHeaderVersion(), entity);
        } else {
            return ResponseCreator.error(404, Error.NOT_FOUND.getCode(),
                    getHeaderVersion());
        }
    }
}
