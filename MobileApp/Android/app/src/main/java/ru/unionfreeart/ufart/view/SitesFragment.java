package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.loaders.ListLoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

public class SitesFragment extends Fragment implements IMasterTask {
    private final String LOADER = "loader", SELECT = "sel";
    private MainActivity activity;
    private LoaderTask loader;
    private ListView lvList;
    private ListAdapter adList;
    private View container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.sites));
        this.container = inflater.inflate(R.layout.fragment_catalog, container, false);
        initViews();
        initButtons();
        restoreFragmentState(savedInstanceState);
        return this.container;
    }

    private void restoreFragmentState(Bundle state) {
        if (state == null) { //first open fragment
            loader = new LoaderTask(SitesFragment.this);
            ILoader loaderSites = new ListLoader(ListRepositories.LIST_SITES);
            loader.execute(loaderSites);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            loader = (LoaderTask) state.getSerializable(LOADER);
            if (loader != null && loader.getStatus() != AsyncTask.Status.RUNNING) {
                loader.newMaster(SitesFragment.this);
            }
            openList();
            adList.setSelectIndex(state.getInt(SELECT));
            adList.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LOADER, loader);
        outState.putInt(SELECT, adList.getSelectIndex());
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        container.findViewById(R.id.pPerson).setVisibility(View.GONE);
        lvList = (ListView) container.findViewById(R.id.lvList);
        adList = new ListAdapter(activity);
        lvList.setAdapter(adList);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adList.setSelectIndex(i);
                adList.notifyDataSetChanged();
            }
        });
    }

    private void initButtons() {
        container.findViewById(R.id.bAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loader = new LoaderTask(SitesFragment.this);
//                ILoader loaderTotal = new TotalLoader(adSites.getId(spSite.getSelectedItemPosition()));
//                loader.execute(loaderTotal);
                activity.setVisibleProgressBar(true);
            }
        });
        container.findViewById(R.id.bEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setVisibleProgressBar(true);
            }
        });
        container.findViewById(R.id.bDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setVisibleProgressBar(true);
            }
        });
    }

    public Context getContext() {
        return activity;
    }

    public void putResult(String msg) {
        activity.setVisibleProgressBar(false);
        if (msg != null) { //error
            Snackbar.make(null, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            openList();
        }
    }

    private void openList() {
        adList.clear();
        ListRepositories sites = new ListRepositories(activity, ListRepositories.LIST_SITES);
        sites.loadList();
        for (int i = 0; i < sites.getCount(); i++) {
            adList.addItem(sites.getItem(i));
        }
        adList.notifyDataSetChanged();
    }
}
