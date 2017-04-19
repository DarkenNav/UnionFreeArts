package ru.unionfreeart.ufart.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.unionfreeart.ufart.database.ListHelper;

public class ListRepositories {
    public static final String LIST_SITES = "sites", LIST_PERSONS = "persons";
    private String list;
    private ListHelper helper;
    private List<String> items = new ArrayList<String>();

    public ListRepositories(Context context, String list) {
        this.list = list;
        helper = new ListHelper(context, list);
    }

    public void loadList() {
        SQLiteDatabase dataBase = helper.getReadableDatabase();
        Cursor cursor = dataBase.query(list, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int iItem = cursor.getColumnIndex(ListHelper.ITEM);
            do {
                add(cursor.getString(iItem));
            } while (cursor.moveToNext());
        }
        helper.close();
    }

    public void saveList() {
        SQLiteDatabase dataBase = helper.getWritableDatabase();
        dataBase.delete(list, null, null); // clear list
        ContentValues row;
        for (int i = 0; i < items.size(); i++) {
            row = new ContentValues();
            row.put(ListHelper.ITEM, items.get(i));
            dataBase.insert(list, null, row);
        }
        helper.close();
    }

    public void add(String item) {
        items.add(item);
    }

    public int getCount() {
        return items.size();
    }

    public String getItem(int i) {
        return (items.size() > i ? items.get(i) : "");
    }
}
