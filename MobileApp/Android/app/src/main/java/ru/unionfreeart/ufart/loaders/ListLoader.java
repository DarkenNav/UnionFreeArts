package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

/**
 * Created by NeoSvet on 24.04.2017.
 */

public class ListLoader  implements ILoader {
    private String name;

    public ListLoader(String name) {
        this.name = name;
    }

    public void run(Context context) {
        String[] mSites = new String[]{"lenta.ru", "site.ru"};
        ListRepositories list = new ListRepositories(context, name);
        for (int i = 0; i < mSites.length; i++) {
            list.add(i, mSites[i]);
        }
        list.saveList();
    }
}
