package ru.unionfreeart.ufart.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TableHelper extends SQLiteOpenHelper {
    public static final String COLUMN_NAME = "name", COLUMN_VALUE = "value";
    private String name;

    public TableHelper(Context context, String name) {
        super(context, name, null, 1);
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + name + " ("
                + COLUMN_NAME + " text,"
                + COLUMN_VALUE + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
