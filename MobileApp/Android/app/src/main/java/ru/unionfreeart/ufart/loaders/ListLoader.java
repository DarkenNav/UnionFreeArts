package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import ru.unionfreeart.ufart.view.Settings;
import ru.unionfreeart.ufart.entities.ListItem;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

/**
 * Created by NeoSvet on 24.04.2017.
 */

public class ListLoader implements ILoader {
    private final int TIMEOUT = 5000;
    private String name;
    private int id = -1;

    public ListLoader(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void run(Context context) throws Exception {
        BasicHttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        Settings settings = new Settings(context);
        HttpGet rget;
        if (id == -1)
            rget = new HttpGet(settings.getAddress() + name + "/");
        else
            rget = new HttpGet(settings.getAddress() + name + "/" + id);
        HttpResponse res = client.execute(rget);
        if (res.getStatusLine().getStatusCode() == 200) { //ok
            JSONArray jsonA = new JSONArray(EntityUtils.toString(res.getEntity()));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ListRepositories list = new ListRepositories(context, name);
            for (int i = 0; i < jsonA.length(); i++) {
                list.add(gson.fromJson(jsonA.get(i).toString(), ListItem.class));
            }
            list.saveList();
        } else { //error
            throw new Exception("Code: " + res.getStatusLine().getStatusCode());
        }
    }
}
