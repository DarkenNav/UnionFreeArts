package ru.unionfreeart.ufart.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.unionfreeart.ufart.database.TableHelper;
import ru.unionfreeart.ufart.entities.TableRow;

public class TableRepositories {
    public static final String TABLE_TOTAL = "total", TABLE_DAILY = "daily";
    private String table_name;
    private TableHelper helper;
    private List<TableRow> table = new ArrayList<TableRow>();

    public TableRepositories(Context context, String name) {
        this.table_name = name;
        helper = new TableHelper(context, name);
    }

    public void clearTable() {
        SQLiteDatabase dataBase = helper.getWritableDatabase();
        dataBase.delete(table_name, null, null);
        helper.close();
    }

    public void loadTable() {
        SQLiteDatabase dataBase = helper.getReadableDatabase();
        Cursor cursor = dataBase.query(table_name, null, null, null, null, null, null);
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
        dataBase.delete(table_name, null, null); // clear table
        ContentValues row;
        String name;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        for (int i = 0; i < table.size(); i++) {
            row = new ContentValues();
            name = table.get(i).getName();
            if (name == null) {
                date = new Date(table.get(i).getDate());
                name = dateFormat.format(date);
            }
            row.put(TableHelper.COLUMN_NAME, name);
            row.put(TableHelper.COLUMN_VALUE, table.get(i).getValue());
            dataBase.insert(table_name, null, row);
        }
        helper.close();
    }

    public void add(String name, String value) {
        table.add(new TableRow(name, value));
    }

    public void add(TableRow row) {
        table.add(row);
    }

    public int getCount() {
        return table.size();
    }

    public String getName(int i) {
        return (table.size() > i ? table.get(i).getName() : "");
    }

    public String getValue(int i) {
        return (table.size() > i ? table.get(i).getValue() : "");
    }
}
