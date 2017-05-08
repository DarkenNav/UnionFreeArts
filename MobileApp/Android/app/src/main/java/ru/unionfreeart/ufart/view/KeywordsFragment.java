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
import android.widget.Spinner;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.loaders.ListLoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;

public class KeywordsFragment extends Fragment implements IMasterTask {
    private final String LOADER = "loader", SELECT = "sel";
    private MainActivity activity;
    private LoaderTask loader;
    private Spinner spPerson;
    private SpinnerAdapter adPerson;
    private ListView lvList;
    private ListAdapter adList;
    private View container;
    private boolean boolLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.sites));
        this.container = inflater.inflate(R.layout.fragment_catalog, container, false);
        initPerson();
        initList();
        initButtons();
        restoreFragmentState(savedInstanceState);
        boolLoad = false;
        return this.container;
    }

    private void restoreFragmentState(Bundle state) {
        if (state == null) { //first open fragment
            loader = new LoaderTask(KeywordsFragment.this);
            ILoader loaderPersons = new ListLoader(ListRepositories.LIST_PERSONS);
            ListLoader loaderKeywords = new ListLoader(ListRepositories.LIST_KEYWORDS);
            loaderKeywords.setId(1);
            loader.execute(loaderPersons, loaderKeywords);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            loader = (LoaderTask) state.getSerializable(LOADER);
            if (loader != null && loader.getStatus() != AsyncTask.Status.RUNNING) {
                loader.newMaster(KeywordsFragment.this);
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

    private void initPerson() {
        spPerson = (Spinner) container.findViewById(R.id.spPerson);
        adPerson = new SpinnerAdapter(activity);
        spPerson.setAdapter(adPerson);
        spPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!boolLoad) {
                    loader = new LoaderTask(KeywordsFragment.this);
                    ListLoader loaderKeywords = new ListLoader(ListRepositories.LIST_KEYWORDS);
                    loaderKeywords.setId(i);
                    loader.execute(loaderKeywords);
                    activity.setVisibleProgressBar(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initList() {
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
            openPerson();
            openList();
        }
    }

    private void openList() {
        adList.clear();
        ListRepositories sites = new ListRepositories(activity, ListRepositories.LIST_KEYWORDS);
        sites.loadList();
        for (int i = 0; i < sites.getCount(); i++) {
            adList.addItem(sites.getItem(i));
        }
        adList.notifyDataSetChanged();
    }

    private void openPerson() {
        if (adPerson.getCount() > 0) //list is not empty
            return;
        ListRepositories persons = new ListRepositories(activity, ListRepositories.LIST_PERSONS);
        persons.loadList();
        for (int i = 0; i < persons.getCount(); i++) {
            adPerson.addItem(persons.getItem(i));
        }
        adPerson.notifyDataSetChanged();
    }
}
