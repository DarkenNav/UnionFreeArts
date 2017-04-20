package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

/**
 * Created by NeoSvet on 19.04.2017.
 */

public class SitesLoaderFake implements ILoader {
    public void run(Context context) {
        String[] mSites = new String[]{"lenta.ru", "site.ru"};
        ListRepositories list = new ListRepositories(context, ListRepositories.LIST_SITES);
        for (int i = 0; i < mSites.length; i++) {
            list.add(i, mSites[i]);
        }
        list.saveList();
    }
}
