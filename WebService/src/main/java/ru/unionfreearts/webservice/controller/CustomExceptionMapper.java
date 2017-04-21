package ru.unionfreearts.webservice.controller;

import ru.unionfreearts.webservice.responseCreator.Error;
import ru.unionfreearts.webservice.responseCreator.ResponseCreator;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Михалыч on 21.04.2017.
 */
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    public Response toResponse(Exception ex) {
        System.out.println(ex.getMessage() + ex.getCause());
        return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(), getHeaderVersion());
    }
}
