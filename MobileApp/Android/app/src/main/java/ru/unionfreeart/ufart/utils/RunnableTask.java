package ru.unionfreeart.ufart.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.interfaces.IRunnable;
import ru.unionfreeart.ufart.interfaces.IMasterTask;


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
            return context.getResources().getString(R.string.load_interrupted_error)
                    + ": " + e.getMessage();
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
        if (msg == null)
            master.putResult(msg);
        else
            master.putResult(msg + "\n" + getTime());
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("(HH:mm:ss dd.MM.yy)");
        return dateFormat.format(new Date());
    }
}
