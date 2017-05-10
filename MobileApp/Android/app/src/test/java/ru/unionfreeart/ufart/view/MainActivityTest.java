package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import ru.unionfreeart.ufart.BuildConfig;
import ru.unionfreeart.ufart.R;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by NeoSvet on 06.05.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    //Before start tests comment "loader.execute(loaderSites" in TotalFragment and DailyFragment

    @Test
    public void useAppContext() throws Exception {
        Context appContext = RuntimeEnvironment.application;
        assertEquals("ru.unionfreeart.ufart", appContext.getPackageName());
    }

    private MainActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void checkSetFragment() throws Exception {
        activity.setFragment(R.id.nav_daily);
        Fragment f = activity.getFragmentManager().findFragmentById(R.id.fragment);
        assertTrue(f instanceof DailyFragment);
    }
}