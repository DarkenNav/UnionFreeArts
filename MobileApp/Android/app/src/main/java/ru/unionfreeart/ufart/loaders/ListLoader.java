package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import ru.unionfreeart.ufart.Settings;
import ru.unionfreeart.ufart.entities.ListItem;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

/**
 * Created by NeoSvet on 24.04.2017.
 */

public class ListLoader implements ILoader {
    private String name;

    public ListLoader(String name) {
        this.name = name;
    }

    public void run(Context context) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            Settings settings = new Settings(context);
            HttpGet rget = new HttpGet(settings.getAddress() + name);
            HttpResponse res = client.execute(rget);
            String r = EntityUtils.toString(res.getEntity());
            ListRepositories list = new ListRepositories(context, name);
            JSONArray jsonA = new JSONArray(r);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (int i = 0; i < jsonA.length(); i++) {
                list.add(gson.fromJson(jsonA.get(i).toString(), ListItem.class));
            }
            list.saveList();
        } catch (Exception e) {
        }
    }
}
