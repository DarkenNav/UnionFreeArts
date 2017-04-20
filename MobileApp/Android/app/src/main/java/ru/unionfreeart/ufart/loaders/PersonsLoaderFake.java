package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

/**
 * Created by NeoSvet on 19.04.2017.
 */

public class PersonsLoaderFake implements ILoader {
    public void run(Context context) {
        String[] mPersons = new String[]{"Putin", "Medvedev"};
        ListRepositories list = new ListRepositories(context, ListRepositories.LIST_PERSONS);
        for (int i = 0; i < mPersons.length; i++) {
            list.add(i, mPersons[i]);
        }
        list.saveList();
    }
}
