package ru.unionfreeart.ufart.loaders;

import android.content.Context;
import android.util.Log;

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
import ru.unionfreeart.ufart.entities.TableRow;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.TableRepositories;

/**
 * Created by NeoSvet on 01.05.2017.
 */

public class TotalLoader implements ILoader {
    private final int TIMEOUT = 5000;
    private int id_site;

    public TotalLoader(int id_site) {
        this.id_site = id_site;
    }

    public void run(Context context) throws Exception {
        BasicHttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        Settings settings = new Settings(context);
        HttpGet rget = new HttpGet(settings.getAddress() + "/stat/total/" + id_site);
        HttpResponse res = client.execute(rget);
        Log.d("neo","code="+res.getStatusLine().getStatusCode());
        if (res.getStatusLine().getStatusCode() == 200) { //ok
            String r = EntityUtils.toString(res.getEntity());
            Log.d("neo",r);
            JSONArray jsonA = new JSONArray(r);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            TableRepositories table = new TableRepositories(context, TableRepositories.TABLE_TOTAL);
            for (int i = 0; i < jsonA.length(); i++) {
                table.add(gson.fromJson(jsonA.get(i).toString(), TableRow.class));
            }
            table.saveTable();
        } else { //error
            throw new Exception("Code: " + res.getStatusLine().getStatusCode());
        }
    }
}