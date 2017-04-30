package ru.unionfreeart.ufart;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by NeoSvet on 30.04.2017.
 */

public class Settings {
    private final String ADDRESS = "address";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public Settings(Context context) {
        pref = context.getSharedPreferences(this.getClass().getSimpleName(), context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public String getAddress() {
        return pref.getString(ADDRESS, "http://192.168.0.104:8080/");
    }

    public void setAddress(String adr) {
        editor.putString(ADDRESS, adr);
        editor.apply();
    }
}
