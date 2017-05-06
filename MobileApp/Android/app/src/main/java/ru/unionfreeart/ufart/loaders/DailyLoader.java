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

import java.util.Date;

import ru.unionfreeart.ufart.view.Settings;
import ru.unionfreeart.ufart.entities.TableRow;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.TableRepositories;

/**
 * Created by NeoSvet on 01.05.2017.
 */

public class DailyLoader implements ILoader {
    private final int TIMEOUT = 5000;
    private int id_site, id_person;
    private Date dateStart, dateFinish;

    public DailyLoader(int id_site, int id_person, Date dateStart, Date dateFinish) {
        this.id_site = id_site;
        this.id_person = id_person;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public void run(Context context) throws Exception {
        BasicHttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
        DefaultHttpClient client = new DefaultHttpClient(httpParameters);
        Settings settings = new Settings(context);
        HttpGet rget = new HttpGet(settings.getAddress() + "/stat/daily?personId="
                + id_person + "&siteId=" + id_site + "&startDate=" + dateStart.getTime() +
                "&finishDate=" + dateFinish.getTime());
        HttpResponse res = client.execute(rget);
        if (res.getStatusLine().getStatusCode() == 200) { //ok
            JSONArray jsonA = new JSONArray(EntityUtils.toString(res.getEntity()));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            TableRepositories table = new TableRepositories(context, TableRepositories.TABLE_DAILY);
            for (int i = 0; i < jsonA.length(); i++) {
                table.add(gson.fromJson(jsonA.get(i).toString(), TableRow.class));
            }
            table.saveTable();
        } else { //error
            throw new Exception("Code: " + res.getStatusLine().getStatusCode());
        }
    }
}
