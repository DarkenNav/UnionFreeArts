package ru.unionfreeart.ufart.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.unionfreeart.ufart.database.TableHelper;

public class TableRepositories {
    public static final String TABLE_TOTAL = "total", TABLE_DAILY = "daily";
    private String table;
    private TableHelper helper;
    private List<String> names = new ArrayList<String>();
    private List<String> values = new ArrayList<String>();

    public TableRepositories(Context context, String table) {
        this.table = table;
        helper = new TableHelper(context, table);
    }

    public void loadTable() {
        SQLiteDatabase dataBase = helper.getReadableDatabase();
        Cursor cursor = dataBase.query(table, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int iName = cursor.getColumnIndex(TableHelper.COLUMN_NAME);
            int iValue = cursor.getColumnIndex(TableHelper.COLUMN_VALUE);
            do {
                add(cursor.getString(iName), cursor.getString(iValue));
            } while (cursor.moveToNext());
        }
        helper.close();
    }

    public void saveTable() {
        SQLiteDatabase dataBase = helper.getWritableDatabase();
        dataBase.delete(table, null, null); // clear table
        ContentValues row;
        for (int i = 0; i < names.size(); i++) {
            row = new ContentValues();
            row.put(TableHelper.COLUMN_NAME, names.get(i));
            row.put(TableHelper.COLUMN_VALUE, values.get(i));
            dataBase.insert(table, null, row);
        }
        helper.close();
    }

    public void add(String name, String value) {
        names.add(name);
        values.add(value);
    }

    public int getCount() {
        return names.size();
    }

    public String getName(int i) {
        return (names.size() > i ? names.get(i) : "");
    }

    public String getValue(int i) {
        return (values.size() > i ? values.get(i) : "");
    }
}
