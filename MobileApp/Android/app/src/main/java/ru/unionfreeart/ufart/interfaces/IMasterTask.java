package ru.unionfreeart.ufart.interfaces;

import android.content.Context;

/**
 * Created by NeoSvet on 19.04.2017.
 */

public interface IMasterTask {
    Context getContext();
    void putResult(String msg);
}
