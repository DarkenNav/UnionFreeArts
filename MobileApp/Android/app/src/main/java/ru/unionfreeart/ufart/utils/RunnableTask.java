package ru.unionfreeart.ufart.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.Serializable;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.interfaces.IRunnable;


/**
 * Created by NeoSvet on 19.04.2017.
 */

public class RunnableTask extends AsyncTask<IRunnable, Integer, String> implements Serializable {
    private transient Context context;
    private transient IMasterTask master;

    public RunnableTask(IMasterTask master) {
        this.master = master;
        this.context = master.getContext();
    }

    public void newMaster(IMasterTask master) {
        this.master = master;
        this.context = master.getContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(IRunnable... loaders) {
        try {
            for (int i = 0; i < loaders.length; i++) {
                loaders[i].run(context);
            }
        } catch (Exception e) {
            final String msg = e.getMessage();
            if (msg.contains("timed out"))
                return context.getResources().getString(R.string.timeout_error);
            if (msg.equals("Code: 404"))
                return context.getResources().getString(R.string.not_found);
            if (msg.equals("Code: 500"))
                return context.getResources().getString(R.string.server_error);
            return context.getResources().getString(R.string.error) + " " + msg;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        master.putResult(msg);
    }
}
