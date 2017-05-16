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
import android.widget.Spinner;
import android.widget.Toast;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;
import ru.unionfreeart.ufart.interfaces.IRunnable;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.runnable.CatalogRunnable;
import ru.unionfreeart.ufart.runnable.ListRunnable;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.RunnableTask;

public class KeywordsFragment extends Fragment implements IMasterTask, InputDialog.Result {
    private final String PERSON = "person", SELECT = "sel";
    private MainActivity activity;
    private RunnableTask task;
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
            task = new RunnableTask(KeywordsFragment.this);
            IRunnable taskPersons = new ListRunnable(ListRepositories.LIST_PERSONS);
            ListRunnable taskKeywords = new ListRunnable(ListRepositories.LIST_KEYWORDS);
            taskKeywords.setId(1);
            task.execute(taskPersons, taskKeywords);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            task = (RunnableTask) state.getSerializable(Const.TASK);
            if (task != null && task.getStatus() != AsyncTask.Status.RUNNING) {
                task.newMaster(KeywordsFragment.this);
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
        outState.putSerializable(Const.TASK, task);
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
                    task = new RunnableTask(KeywordsFragment.this);
                    ListRunnable taskKeywords = new ListRunnable(ListRepositories.LIST_KEYWORDS);
                    taskKeywords.setId(i);
                    task.execute(taskKeywords);
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
                if (adPerson.getCount() == 0)
                    return;
                InputDialog dialog = new InputDialog(activity);
                dialog.setResult(KeywordsFragment.this);
                dialog.show();
            }
        });
        container.findViewById(R.id.bEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCorrectIndex()) {
                    InputDialog dialog = new InputDialog(activity);
                    dialog.setResult(KeywordsFragment.this);
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
        task = new RunnableTask(KeywordsFragment.this);
        IRunnable catalogTask = new CatalogRunnable(ListRepositories.LIST_KEYWORDS,
                Const.DELETE, adList.getSelectName(), person, adList.getSelectIndex());
        ListRunnable taskKeywords = new ListRunnable(ListRepositories.LIST_KEYWORDS);
        taskKeywords.setId(person);
        task.execute(catalogTask, taskKeywords);
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

    @Override
    public void putString(int action, String input) {
        if (action == Const.CANCEL)
            return;
        task = new RunnableTask(KeywordsFragment.this);
        CatalogRunnable catalogRunnable;
        if (action == Const.ADD) {
            catalogRunnable = new CatalogRunnable(ListRepositories.LIST_KEYWORDS,
                    Const.ADD, input, person, -1);
        } else { //action == Const.EDIT
            catalogRunnable = new CatalogRunnable(ListRepositories.LIST_KEYWORDS,
                    Const.EDIT, input, person, adList.getSelectIndex());
        }
        ListRunnable taskKeywords = new ListRunnable(ListRepositories.LIST_KEYWORDS);
        taskKeywords.setId(person);
        task.execute(catalogRunnable, taskKeywords);
        activity.setVisibleProgressBar(true);
    }
}
