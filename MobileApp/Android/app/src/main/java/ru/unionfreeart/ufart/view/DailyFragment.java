package ru.unionfreeart.ufart.view;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;
import ru.unionfreeart.ufart.entities.TableAdapter;
import ru.unionfreeart.ufart.entities.TableRow;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.interfaces.IRunnable;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.repositories.TableRepositories;
import ru.unionfreeart.ufart.runnable.DailyRunnable;
import ru.unionfreeart.ufart.runnable.ListRunnable;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.RunnableTask;

public class DailyFragment extends Fragment implements View.OnClickListener, IMasterTask {
    private final String START = "start", FINISH = "finish",
            SITE_POSITON = "site", PERSON_POSITON = "person", VISIBLE_OPTIONS = "options";
    private final int MIN_DAY = 1, MIN_MONTH = 3, MIN_YEAR = 2017;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private DatePickerDialog.OnDateSetListener dateStartListener, dateFinishListener;
    private MainActivity activity;
    private RunnableTask task;
    private Spinner spSite, spPerson;
    private SpinnerAdapter adSites, adPerson;
    private TableAdapter adTable;
    private EditText etStartDate, etFinishDate;
    private Date dateStart, dateFinish;
    private View container, pOptions, pList, fabOptions, fabOk, fabGraph;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.daily_statistics));
        this.container = inflater.inflate(R.layout.fragment_statistics, container, false);
        initSites();
        initPersons();
        initTable();
        initPanels();
        initButtons();
        initPreferences();
        initDates();
        restoreFragmentState(savedInstanceState);
        return this.container;
    }

    private void restoreFragmentState(Bundle state) {
        if (state == null) { //first open fragment
            pList.setVisibility(View.GONE);
            pOptions.setVisibility(View.VISIBLE);
            TableRepositories table = new TableRepositories(activity, TableRepositories.TABLE_DAILY);
            table.clearTable();
            task = new RunnableTask(DailyFragment.this);
            IRunnable loaderSites = new ListRunnable(ListRepositories.LIST_SITES);
            IRunnable loaderPersons = new ListRunnable(ListRepositories.LIST_PERSONS);
            task.execute(loaderSites, loaderPersons);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            task = (RunnableTask) state.getSerializable(Const.TASK);
            if (task != null && task.getStatus() != AsyncTask.Status.RUNNING) {
                task.newMaster(DailyFragment.this);
            }
            openLists();
            openTable();
            int pos = state.getInt(SITE_POSITON);
            if (pos == -1) {
                fabOk.setVisibility(View.GONE);
                fabOptions.setVisibility(View.GONE);
                return;
            }
            spSite.setSelection(pos);
            spPerson.setSelection(state.getInt(PERSON_POSITON));
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
        outState.putInt(PERSON_POSITON, spPerson.getSelectedItemPosition());
        outState.putBoolean(VISIBLE_OPTIONS, pOptions.getVisibility() == View.VISIBLE);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        editor.putLong(START, dateStart.getTime());
        editor.putLong(FINISH, dateFinish.getTime());
        editor.apply();
    }

    private void initPanels() {
        pOptions = container.findViewById(R.id.pOptions);
        pList = container.findViewById(R.id.pList);
    }

    private void initPreferences() {
        pref = activity.getSharedPreferences(this.getClass().getSimpleName(), activity.MODE_PRIVATE);
        editor = pref.edit();
    }

    private void initButtons() {
        fabOk = container.findViewById(R.id.fabOk);
        fabOptions = container.findViewById(R.id.fabOptions);
        fabGraph = container.findViewById(R.id.fabGraph);
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new RunnableTask(DailyFragment.this);
                IRunnable loaderDaily = new DailyRunnable(
                        adSites.getId(spSite.getSelectedItemPosition()),
                        adPerson.getId(spPerson.getSelectedItemPosition()),
                        dateStart, dateFinish);
                task.execute(loaderDaily);
                fabOk.setVisibility(View.GONE);
                activity.setVisibleProgressBar(true);
            }
        });
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabGraph.setVisibility(View.GONE);
                fabOptions.setVisibility(View.GONE);
                fabOk.setVisibility(View.VISIBLE);
                pList.setVisibility(View.GONE);
                pOptions.setVisibility(View.VISIBLE);
            }
        });
        fabGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GraphActivity.open(activity, TableRepositories.TABLE_DAILY);
            }
        });
    }

    private void initSites() {
        spSite = (Spinner) container.findViewById(R.id.spSite);
        adSites = new SpinnerAdapter(activity);
        spSite.setAdapter(adSites);
    }

    private void initPersons() {
        spPerson = (Spinner) container.findViewById(R.id.spPerson);
        adPerson = new SpinnerAdapter(activity);
        spPerson.setAdapter(adPerson);
    }

    private void initDates() {
        long now = System.currentTimeMillis();
        dateStart = new Date(pref.getLong(START, now));
        dateFinish = new Date(pref.getLong(FINISH, now));

        etStartDate = (EditText) container.findViewById(R.id.etStartDate);
        etStartDate.setText(dateFormat.format(dateStart));
        etStartDate.setOnClickListener(this);

        etFinishDate = (EditText) container.findViewById(R.id.etFinishDate);
        etFinishDate.setText(dateFormat.format(dateFinish));
        etFinishDate.setOnClickListener(this);

        dateStartListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateStart = new Date(getYearForDate(year), month, day);
                etStartDate.setText(dateFormat.format(dateStart));
            }
        };
        dateFinishListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateFinish = new Date(getYearForDate(year), month, day);
                etFinishDate.setText(dateFormat.format(dateFinish));
            }
        };
    }

    private void initTable() {
        ListView lvTable = (ListView) container.findViewById(R.id.lvTable);
        adTable = new TableAdapter(activity);
        lvTable.setAdapter(adTable);
    }


    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog;
        if (view.getId() == R.id.etStartDate) {
            datePickerDialog = new DatePickerDialog(activity, dateStartListener,
                    getYearFromDate(dateStart), dateStart.getMonth(), dateStart.getDate());
            Date min = new Date(getYearForDate(MIN_YEAR), MIN_MONTH, MIN_DAY);
            datePickerDialog.getDatePicker().setMinDate(min.getTime());
            datePickerDialog.getDatePicker().setMaxDate(dateFinish.getTime());
        } else { //if (view.getId() == R.id.etFinishDate)
            datePickerDialog = new DatePickerDialog(activity, dateFinishListener,
                    getYearFromDate(dateFinish), dateFinish.getMonth(), dateFinish.getDate());
            datePickerDialog.getDatePicker().setMinDate(dateStart.getTime());
            Date max = new Date();
            datePickerDialog.getDatePicker().setMaxDate(max.getTime());
        }
        datePickerDialog.show();
    }

    private int getYearForDate(int year) {
        return year - 1900;
    }

    private int getYearFromDate(Date date) {
        return date.getYear() + 1900;
    }

    public Context getContext() {
        return activity;
    }

    public void putResult(String msg) {
        activity.setVisibleProgressBar(false);
        if (msg != null) { //error
            Snackbar.make(fabOptions, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            if (adSites.getCount() == 0) {
                fabOk.setVisibility(View.GONE);
                fabOptions.setVisibility(View.GONE);
            } else
                fabOk.setVisibility(View.VISIBLE);
        } else {
            pOptions.setVisibility(View.GONE);
            fabOptions.setVisibility(View.VISIBLE);
            pList.setVisibility(View.VISIBLE);
            openLists();
            openTable();
        }
    }

    private void openTable() {
        adTable.clear();
        TableRepositories table = new TableRepositories(activity, TableRepositories.TABLE_DAILY);
        table.loadTable();
        if (table.getCount() == 0) { //table is empty
            pOptions.setVisibility(View.VISIBLE);
            return;
        }
        fabGraph.setVisibility(View.VISIBLE);
        pList.setVisibility(View.VISIBLE);
        adTable.addItem(new TableRow(getResources().getString(R.string.date),
                getResources().getString(R.string.count_mentions)));
        adTable.getItem(0).setBold(true);
        int k = 0;
        for (int i = 0; i < table.getCount(); i++) {
            adTable.addItem(new TableRow(table.getName(i), table.getValue(i)));
            k += Integer.parseInt(table.getValue(i));
        }
        adTable.addItem(new TableRow(getResources().getString(R.string.total_for_period), k));
        adTable.getItem(adTable.getCount() - 1).setBold(true);
        adTable.notifyDataSetChanged();
    }

    private void openLists() {
        if (adSites.getCount() > 0) //lists is not empty
            return;
        ListRepositories sites = new ListRepositories(activity, ListRepositories.LIST_SITES);
        sites.loadList();
        for (int i = 0; i < sites.getCount(); i++) {
            adSites.addItem(sites.getItem(i));
        }
        adSites.notifyDataSetChanged();
        ListRepositories persons = new ListRepositories(activity, ListRepositories.LIST_PERSONS);
        persons.loadList();
        for (int i = 0; i < persons.getCount(); i++) {
            adPerson.addItem(persons.getItem(i));
        }
        adPerson.notifyDataSetChanged();
    }
}
