package ru.unionfreeart.ufart;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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

import ru.unionfreeart.ufart.entities.ListAdapter;
import ru.unionfreeart.ufart.entities.TableAdapter;
import ru.unionfreeart.ufart.entities.TableRow;
import ru.unionfreeart.ufart.interfaces.ILoader;
import ru.unionfreeart.ufart.interfaces.IMasterTask;
import ru.unionfreeart.ufart.loaders.DailyLoaderFake;
import ru.unionfreeart.ufart.loaders.ListLoader;
import ru.unionfreeart.ufart.repositories.ListRepositories;
import ru.unionfreeart.ufart.repositories.TableRepositories;

public class DailyFragment extends Fragment implements View.OnClickListener, IMasterTask {
    private final String START = "start", FINISH = "finish", LOADER = "loader",
            SITE_POSITON = "site", PERSON_POSITON = "person", VISIBLE_OPTIONS = "options";
    private final int MIN_DAY = 1, MIN_MONTH = 3, MIN_YEAR = 2017;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private DatePickerDialog.OnDateSetListener dateStartListener, dateFinishListener;
    private MainActivity activity;
    private LoaderTask loader;
    private Spinner spSite, spPerson;
    private ListAdapter adSites, adPerson;
    private TableAdapter adTable;
    private EditText etStartDate, etFinishDate;
    private Date dateStart, dateFinish;
    private View container, pOptions, pList, fabOptions, fabOk;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.daily_statistics));
        this.container = inflater.inflate(R.layout.fragment_daily, container, false);
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
            loader = new LoaderTask(DailyFragment.this);
            ILoader loaderSites = new ListLoader(ListRepositories.LIST_SITES);
            ILoader loaderPersons = new ListLoader(ListRepositories.LIST_PERSONS);
            loader.execute(loaderSites, loaderPersons);
            activity.setVisibleProgressBar(true);
        } else { //restore fragment
            loader = (LoaderTask) state.getSerializable(LOADER);
            if (loader != null && loader.getStatus() != AsyncTask.Status.RUNNING) {
                loader.newMaster(DailyFragment.this);
            }
            openLists();
            openTable();
            spSite.setSelection(state.getInt(SITE_POSITON));
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
        outState.putSerializable(LOADER, loader);
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
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loader = new LoaderTask(DailyFragment.this);
                ILoader loaderDaily = new DailyLoaderFake(
                        adSites.getId(spSite.getSelectedItemPosition()),
                        adPerson.getId(spPerson.getSelectedItemPosition()),
                        dateStart, dateFinish);
                loader.execute(loaderDaily);
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
        adSites = new ListAdapter(activity);
        spSite.setAdapter(adSites);
    }

    private void initPersons() {
        spPerson = (Spinner) container.findViewById(R.id.spPerson);
        adPerson = new ListAdapter(activity);
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
        pOptions.setVisibility(View.GONE);
        fabOptions.setVisibility(View.VISIBLE);
        pList.setVisibility(View.VISIBLE);

        openLists();
        openTable();
    }

    private void openTable() {
        adTable.clear();
        TableRepositories table = new TableRepositories(activity, TableRepositories.TABLE_DAILY);
        table.loadTable();
        if (table.getCount() == 0) { //table is empty
            pOptions.setVisibility(View.VISIBLE);
            return;
        }
        pList.setVisibility(View.VISIBLE);
        adTable.addItem(new TableRow(getResources().getString(R.string.date),
                getResources().getString(R.string.count_new_pages)));
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
