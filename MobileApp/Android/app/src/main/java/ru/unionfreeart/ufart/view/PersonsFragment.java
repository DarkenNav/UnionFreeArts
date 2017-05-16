package ru.unionfreeart.ufart.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.interfaces.IRunnable;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.runnable.CatalogRunnable;
import ru.unionfreeart.ufart.runnable.ListRunnable;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.RunnableTask;

public class PersonsFragment extends Fragment implements IMasterTask, InputDialog.Result {
    private final String SELECT = "sel";
    private MainActivity activity;
    private RunnableTask task;
    private ListView lvList;
    private ListAdapter adList;
    private View container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.persons));
        this.container = inflater.inflate(R.layout.fragment_catalog, container, false);
        initViews();
        initButtons();
        restoreFragmentState(savedInstanceState);
        return this.container;
    }

    private void restoreFragmentState(Bundle state) {
        if (state == null) { //first open fragment
            task = new RunnableTask(PersonsFragment.this);
            IRunnable taskPersons = new ListRunnable(ListRepositories.LIST_PERSONS);
            task.execute(taskPersons);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            task = (RunnableTask) state.getSerializable(Const.TASK);
            if (task != null && task.getStatus() != AsyncTask.Status.RUNNING) {
                task.newMaster(PersonsFragment.this);
            }
            openList();
            adList.setSelectIndex(state.getInt(SELECT));
            adList.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(Const.TASK, task);
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
                InputDialog dialog = new InputDialog(activity);
                dialog.setResult(PersonsFragment.this);
                dialog.show();
            }
        });
        container.findViewById(R.id.bEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCorrectIndex()) {
                    InputDialog dialog = new InputDialog(activity);
                    dialog.setResult(PersonsFragment.this);
                    dialog.show(adList.getSelectName());
                }
            }
        });
        container.findViewById(R.id.bDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCorrectIndex()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(getResources().getString(R.string.delete) + "?");
                    builder.setMessage(adList.getSelectName());
                    builder.setNegativeButton(getResources().getString(android.R.string.no),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.setPositiveButton(getResources().getString(android.R.string.yes),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    deleteItem();
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }
            }
        });
    }

    private void deleteItem() {
        task = new RunnableTask(PersonsFragment.this);
        IRunnable catalogTask = new CatalogRunnable(ListRepositories.LIST_PERSONS,
                Const.DELETE, adList.getSelectName(), adList.getSelectId());
        IRunnable taskPersons = new ListRunnable(ListRepositories.LIST_PERSONS);
        task.execute(catalogTask, taskPersons);
        activity.setVisibleProgressBar(true);
    }

    private boolean isCorrectIndex() {
        if(adList.getSelectIndex() > -1)
            return true;
        else
            Toast.makeText(activity, getResources().getString(R.string.need_select), Toast.LENGTH_LONG).show();
        return false;
    }

    public Context getContext() {
        return activity;
    }

    public void putResult(String msg) {
        activity.setVisibleProgressBar(false);
        if (msg != null) { //error
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
        } else {
            openList();
        }
    }

    private void openList() {
        adList.clear();
        ListRepositories persons = new ListRepositories(activity, ListRepositories.LIST_PERSONS);
        persons.loadList();
        for (int i = 0; i < persons.getCount(); i++) {
            adList.addItem(persons.getItem(i));
        }
        adList.notifyDataSetChanged();
    }

    @Override
    public void putString(int action, String input) {
        if (action == Const.CANCEL)
            return;
        task = new RunnableTask(PersonsFragment.this);
        CatalogRunnable catalogRunnable;
        if (action == Const.ADD) {
            catalogRunnable = new CatalogRunnable(ListRepositories.LIST_PERSONS, input);
        } else { //action == Const.EDIT
            catalogRunnable = new CatalogRunnable(ListRepositories.LIST_PERSONS,
                    Const.EDIT, input, adList.getSelectId());
        }
        IRunnable taskPersons = new ListRunnable(ListRepositories.LIST_PERSONS);
        task.execute(catalogRunnable, taskPersons);
        activity.setVisibleProgressBar(true);
    }
}
