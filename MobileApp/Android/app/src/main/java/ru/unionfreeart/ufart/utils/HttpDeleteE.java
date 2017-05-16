package ru.unionfreeart.ufart.utils;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * Created by NeoSvet on 16.05.2017.
 */

@NotThreadSafe
public class HttpDeleteE extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDeleteE(String uri) {
        super();
        setURI(URI.create(uri));
    }
}
