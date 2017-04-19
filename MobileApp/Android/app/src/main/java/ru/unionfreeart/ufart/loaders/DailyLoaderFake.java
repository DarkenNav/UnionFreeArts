package ru.unionfreeart.ufart.loaders;

import android.content.Context;

import java.util.Date;

import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.repositories.TableRepositories;

/**
 * Created by NeoSvet on 19.04.2017.
 */

public class DailyLoaderFake implements ILoader {
    private int id_site, id_person;
    private Date dateStart, dateFinish;

    public DailyLoaderFake(int id_site, int id_person, Date dateStart, Date dateFinish) {
        this.id_site = id_site;
        this.id_person = id_person;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public void run(Context context) {
        String[] mDates = new String[]{"15.02.2015", "16.02.2015", "17.02.2015", "18.02.2015", "18.02.2015",
                "19.02.2015", "20.02.2015"};
        int[] mCount = new int[]{1, 2, 0, 4, 1, 9, 2};
        TableRepositories table = new TableRepositories(context, TableRepositories.TABLE_DAILY);
        for (int i = 0; i < mDates.length; i++) {
            table.add(mDates[i], String.valueOf(mCount[i]));
        }
        table.saveTable();
    }
}
