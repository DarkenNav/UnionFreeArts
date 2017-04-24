package ru.unionfreeart.ufart.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.unionfreeart.ufart.database.ListHelper;
import ru.unionfreeart.ufart.entities.ListItem;

public class ListRepositories {
    public static final String LIST_SITES = "site", LIST_PERSONS = "person";
    private String list;
    private ListHelper helper;
    private List<ListItem> items = new ArrayList<ListItem>();

    public ListRepositories(Context context, String list) {
        this.list = list;
        helper = new ListHelper(context, list);
    }

    public void loadList() {
        SQLiteDatabase dataBase = helper.getReadableDatabase();
        Cursor cursor = dataBase.query(list, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int iName = cursor.getColumnIndex(ListHelper.NAME);
            int iID = cursor.getColumnIndex(ListHelper.ID);
            do {
                add(cursor.getInt(iID), cursor.getString(iName));
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
            row.put(ListHelper.ID, items.get(i).getId());
            row.put(ListHelper.NAME, items.get(i).getName());
            dataBase.insert(list, null, row);
        }
        helper.close();
    }

    public void add(int id, String name) {
        items.add(new ListItem(id, name));
    }

    public int getCount() {
        return items.size();
    }

    public ListItem getItem(int i) {
        return (items.size() > i ? items.get(i) : null);
    }
}
