package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.Settings;

/**
 * Created by NeoSvet on 08.05.2017.
 */

public class CatalogTask implements ILoader {
    private final String PARAMETER_NAME = "data";
    private String name, reqest;
    private int method;

    public CatalogTask(String name, String item) {
        this.name = name;
        method = Const.ADD;
        reqest = "{\"name\": \"" + item + "\"}";
    }

    public CatalogTask(String name, int method, String item, int id) {
        this.name = name;
        this.method = method;
        reqest = "{\"id\": " + id + ", \"name\": \"" + item + "\"}";
    }

    public CatalogTask(String name, int method, String item, int person, int id) {
        this.name = name;
        this.method = method;
        if (id > 0)
            reqest = "{\"id\": " + id + ", \"name\": \"" + item + "\", \"personId\": " + person + "}";
        else
            reqest = "{\"name\": \"" + item + "\", \"personId\": " + person + "}";
    }

    public void run(Context context) throws Exception {
        BasicHttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, Const.TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, Const.TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        Settings settings = new Settings(context);
        HttpResponse res;
        httpParameters = new BasicHttpParams();
        httpParameters.setParameter(PARAMETER_NAME, reqest);
        switch (method) {
            case Const.ADD:
                HttpPost post = new HttpPost(settings.getAddress() + name + "/");
                post.setParams(httpParameters);
                res = client.execute(post);
                break;
            case Const.EDIT:
                HttpPut put = new HttpPut(settings.getAddress() + name + "/");
                put.setParams(httpParameters);
                res = client.execute(put);
                break;
            default: //case Const.DELETE:
                HttpDelete del = new HttpDelete(settings.getAddress() + name + "/");
                del.setParams(httpParameters);
                res = client.execute(del);
                break;
        }
        if (res.getStatusLine().getStatusCode() > 201) { // error
            throw new Exception("Code: " + res.getStatusLine().getStatusCode());
        }
    }
}
