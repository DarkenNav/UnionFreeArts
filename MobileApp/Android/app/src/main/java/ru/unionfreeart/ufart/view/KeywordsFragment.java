package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.loaders.ListLoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.utils.LoaderTask;

public class KeywordsFragment extends Fragment implements IMasterTask {
    private final String LOADER = "loader", PERSON = "person", SELECT = "sel";
    private MainActivity activity;
    private LoaderTask loader;
    private Spinner spPerson;
    private SpinnerAdapter adPerson;
    private ListView lvList;
    private ListAdapter adList;
    private int person = 0;
    private View container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.keywords));
        this.container = inflater.inflate(R.layout.fragment_catalog, container, false);
        initPerson();
        initList();
        initButtons();
        restoreFragmentState(savedInstanceState);
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
            openPerson();
            person = state.getInt(PERSON);
            spPerson.setSelection(person);
            openList();
            adList.setSelectIndex(state.getInt(SELECT));
            adList.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LOADER, loader);
        outState.putInt(SELECT, adList.getSelectIndex());
        outState.putInt(PERSON, person);
        super.onSaveInstanceState(outState);
    }

    private void initPerson() {
        spPerson = (Spinner) container.findViewById(R.id.spPerson);
        adPerson = new SpinnerAdapter(activity);
        spPerson.setAdapter(adPerson);
        spPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != person) {
                    adList.setSelectIndex(-1);
                    loader = new LoaderTask(KeywordsFragment.this);
                    ListLoader loaderKeywords = new ListLoader(ListRepositories.LIST_KEYWORDS);
                    loaderKeywords.setId(i);
                    loader.execute(loaderKeywords);
                    activity.setVisibleProgressBar(true);
                    person = i;
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
            Toast.makeText(activity, msg, Toast.LENGTH_LONG);
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
