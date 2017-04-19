package ru.unionfreeart.ufart;

import android.content.Context;
import android.os.AsyncTask;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.interfaces.IMasterTask;


/**
 * Created by NeoSvet on 19.04.2017.
 */

public class Task extends AsyncTask<ILoader, Integer, String> implements Serializable {
    private Context context;
    private IMasterTask master;

    public Task(Context context, IMasterTask master) {
        this.context = context;
        this.master = master;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(ILoader... loaders) {
        try {
            for (int i = 0; i < loaders.length; i++) {
                loaders[i].run(context);
            }
        } catch (Exception e) {
            return context.getResources().getString(R.string.task_interrupted_error)
                    + ": " + e.getMessage();
        }
        return context.getResources().getString(R.string.task_completed_successfully);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        master.putResult(msg + getTime());
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat(" (HH:mm:ss dd.MM.yy)");
        return dateFormat.format(new Date());
    }
}
