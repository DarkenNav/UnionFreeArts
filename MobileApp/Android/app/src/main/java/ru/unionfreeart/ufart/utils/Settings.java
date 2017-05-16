package ru.unionfreeart.ufart.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NeoSvet on 30.04.2017.
 */

public class Settings {
    private final String ADDRESS = "address", LOGIN = "login", PASSWORD = "password";
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

    public String getLogin() {
        return pref.getString(LOGIN, "");
    }

    public void setLogin(String login) {
        editor.putString(LOGIN, login);
        editor.apply();
    }

    public String getPassword() {
        return pref.getString(PASSWORD, "");
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password);
        editor.apply();
    }
}
