package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;
import ru.unionfreeart.ufart.entities.TableAdapter;
import ru.unionfreeart.ufart.entities.TableRow;
import ru.unionfreeart.ufart.interfaces.IRunnable;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.runnable.ListRunnable;
import ru.unionfreeart.ufart.runnable.TotalRunnable;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.repositories.TableRepositories;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.RunnableTask;

public class TotalFragment extends Fragment implements IMasterTask {
    private final String SITE_POSITON = "site", VISIBLE_OPTIONS = "options";
    private MainActivity activity;
    private RunnableTask task;
    private Spinner spSite;
    private SpinnerAdapter adSites;
    private TableAdapter adTable;
    private View container, pOptions, pList, fabOptions, fabOk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.total_statistics));
        this.container = inflater.inflate(R.layout.fragment_statistics, container, false);
        initSites();
        initTable();
        initPanels();
        initButtons();
        restoreFragmentState(savedInstanceState);
        return this.container;
    }

    private void restoreFragmentState(Bundle state) {
        if (state == null) { //first open fragment
            pList.setVisibility(View.GONE);
            pOptions.setVisibility(View.VISIBLE);
            TableRepositories table = new TableRepositories(activity, TableRepositories.TABLE_TOTAL);
            table.clearTable();
            task = new RunnableTask(TotalFragment.this);
            IRunnable taskSites = new ListRunnable(ListRepositories.LIST_SITES);
            task.execute(taskSites);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            task = (RunnableTask) state.getSerializable(Const.TASK);
            if (task != null && task.getStatus() != AsyncTask.Status.RUNNING) {
                task.newMaster(TotalFragment.this);
            }
            openList();
            openTable();
            spSite.setSelection(state.getInt(SITE_POSITON));
            if (state.getBoolean(VISIBLE_OPTIONS)) {
                pList.setVisibility(View.GONE);
                fabOptions.setVisibility(View.GONE);
            } else {
                pOptions.setVisibility(View.GONE);
                fabOk.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(Const.TASK, task);
        outState.putInt(SITE_POSITON, spSite.getSelectedItemPosition());
        outState.putBoolean(VISIBLE_OPTIONS, pOptions.getVisibility() == View.VISIBLE);
        super.onSaveInstanceState(outState);
    }

    private void initPanels() {
        container.findViewById(R.id.pPeriod).setVisibility(View.GONE);
        container.findViewById(R.id.pPerson).setVisibility(View.GONE);
        pOptions = container.findViewById(R.id.pOptions);
        pList = container.findViewById(R.id.pList);
    }

    private void initButtons() {
        fabOk = container.findViewById(R.id.fabOk);
        fabOptions = container.findViewById(R.id.fabOptions);
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new RunnableTask(TotalFragment.this);
                IRunnable taskTotal = new TotalRunnable(adSites.getId(spSite.getSelectedItemPosition()));
                task.execute(taskTotal);
                fabOk.setVisibility(View.GONE);
                activity.setVisibleProgressBar(true);
            }
        });
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabOptions.setVisibility(View.GONE);
                fabOk.setVisibility(View.VISIBLE);
                pList.setVisibility(View.GONE);
                pOptions.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initSites() {
        spSite = (Spinner) container.findViewById(R.id.spSite);
        adSites = new SpinnerAdapter(activity);
        spSite.setAdapter(adSites);
    }

    private void initTable() {
        ListView lvTable = (ListView) container.findViewById(R.id.lvTable);
        adTable = new TableAdapter(activity);
        lvTable.setAdapter(adTable);
    }

    public Context getContext() {
        return activity;
    }

    public void putResult(String msg) {
        activity.setVisibleProgressBar(false);
        if (msg != null) { //error
            fabOk.setVisibility(View.VISIBLE);
            Snackbar.make(fabOptions, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            pOptions.setVisibility(View.GONE);
            fabOptions.setVisibility(View.VISIBLE);
            pList.setVisibility(View.VISIBLE);
            openList();
            openTable();
        }
    }

    private void openTable() {
        adTable.clear();
        TableRepositories table = new TableRepositories(activity, TableRepositories.TABLE_TOTAL);
        table.loadTable();
        if (table.getCount() == 0) { //table is empty
            pOptions.setVisibility(View.VISIBLE);
            return;
        }
        pList.setVisibility(View.VISIBLE);
        adTable.addItem(new TableRow(getResources().getString(R.string.name),
                getResources().getString(R.string.count_mentions)));
        adTable.getItem(0).setBold(true);
        for (int i = 0; i < table.getCount(); i++) {
            adTable.addItem(new TableRow(table.getName(i), table.getValue(i)));
        }
        adTable.notifyDataSetChanged();
    }

    private void openList() {
        if (adSites.getCount() > 0) //lists is not empty
            return;
        ListRepositories sites = new ListRepositories(activity, ListRepositories.LIST_SITES);
        sites.loadList();
        for (int i = 0; i < sites.getCount(); i++) {
            adSites.addItem(sites.getItem(i));
        }
        adSites.notifyDataSetChanged();
    }
}
