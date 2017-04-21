package ru.unionfreearts.webservice.responseCreator;

import javax.ws.rs.core.Response;

/**
 * Created by Михалыч on 21.04.2017.
 */
public class ResponseCreator {

    public static Response error(int status, int errorCode, String version) {
        Response.ResponseBuilder response = Response.status(status);
        response.header("version", version);
        response.header("errorcode", errorCode);
        response.entity("none");
        return response.build();
    }

    public static Response success(String version, Object object) {
        Response.ResponseBuilder response = Response.ok();
        response.header("version", version);
        if (object != null) {
            response.entity(object);
        } else {
            response.entity("none");
        }
        return response.build();
    }
}
