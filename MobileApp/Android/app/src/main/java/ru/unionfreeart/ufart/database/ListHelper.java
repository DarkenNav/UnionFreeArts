package ru.unionfreeart.ufart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListHelper extends SQLiteOpenHelper {
    public static final String NAME = "name", ID = "id";
    private String name;

    public ListHelper(Context context, String name) {
        super(context, name, null, 1);
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + name + " ("
                + ID + " integer primary key, "
                + NAME + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
