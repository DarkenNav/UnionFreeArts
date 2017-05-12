package ru.unionfreeart.ufart.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.unionfreeart.ufart.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String USER = "user";
    private final int INDEX_ADMIN = 3;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private ProgressBar progressBar;
    private boolean boolUser;

    public static void open(Context context, boolean boolUser) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER, boolUser);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initAuthorization();
        restoreActivityState(savedInstanceState);
    }

    private void initAuthorization() {
        boolUser = getIntent().getBooleanExtra(USER, true);
        TextView tvLogin = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvLogim);
        if (boolUser) {
            tvLogin.setText(getResources().getString(R.string.user));
            for (int i = INDEX_ADMIN; i <  navigationView.getMenu().size(); i++) {
                navigationView.getMenu().getItem(i).setVisible(false);
            }
        } else {
            tvLogin.setText(getResources().getString(R.string.admin));
            for (int i = 1; i < INDEX_ADMIN; i++) {
                navigationView.getMenu().getItem(i).setVisible(false);
            }
        }
        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }

    private void restoreActivityState(Bundle state) {
        if (state == null) { //first open activity
            if (boolUser)
                setFragment(R.id.nav_total);
            else
                setFragment(R.id.nav_persons);
        } else { //restore activity
            //fragment will be restored automatically
        }
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getFragmentManager();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        progressBar = (ProgressBar) findViewById(R.id.toolbar_progress_bar);
    }

    public void setVisibleProgressBar(boolean visible) {
        if (visible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        setFragment(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(int id) {
        if (navigationView != null)
            navigationView.setCheckedItem(id);
        setVisibleProgressBar(false);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.nav_total:
                fragmentTransaction.replace(R.id.fragment, new TotalFragment());
                break;
            case R.id.nav_daily:
                fragmentTransaction.replace(R.id.fragment, new DailyFragment());
                break;
            case R.id.nav_reg:
                fragmentTransaction.replace(R.id.fragment, new RegFragment());
                break;
            case R.id.nav_persons:
                fragmentTransaction.replace(R.id.fragment, new PersonsFragment());
                break;
            case R.id.nav_keywords:
                fragmentTransaction.replace(R.id.fragment, new KeywordsFragment());
                break;
            case R.id.nav_sites:
                fragmentTransaction.replace(R.id.fragment, new SitesFragment());
                break;
            case R.id.nav_settings:
                fragmentTransaction.replace(R.id.fragment, new SettingsFragment());
                break;
        }
        fragmentTransaction.commit();
    }
}
