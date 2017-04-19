package ru.unionfreeart.ufart;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.unionfreeart.ui.TableAdapter;
import ru.unionfreeart.ui.TableRow;

public class DailyFragment extends Fragment implements View.OnClickListener {
    private final int MIN_DAY = 1, MIN_MONTH = 3, MIN_YEAR = 2017;
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private DatePickerDialog.OnDateSetListener dateStartListener, dateFinishListener;
    private MainActivity act;
    private TableAdapter adTable;
    private EditText etStartDate, etFinishDate;
    private Date dateStart, dateFinish;
    private View container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        act = (MainActivity) getActivity();
        act.setTitle(getResources().getString(R.string.daily_statistics));
        this.container = inflater.inflate(R.layout.fragment_daily, container, false);
        initSites();
        initPersons();
        initDates();
        initTable();
        return this.container;
    }

    private void initSites() {
        Spinner spSite = (Spinner) container.findViewById(R.id.spSite);
        ArrayAdapter<String> adSites = new ArrayAdapter<String>(act, android.R.layout.simple_spinner_dropdown_item);
        adSites.add("lenta.ru");
        adSites.add("site.ru");
        spSite.setAdapter(adSites);
    }

    private void initPersons() {
        Spinner spPerson = (Spinner) container.findViewById(R.id.spPerson);
        ArrayAdapter<String> adPerson = new ArrayAdapter<String>(act, android.R.layout.simple_spinner_dropdown_item);
        adPerson.add("Путин");
        adPerson.add("Медведев");
        spPerson.setAdapter(adPerson);
    }

    private void initDates() {
        dateStart = new Date();
        etStartDate = (EditText) container.findViewById(R.id.etStartDate);
        etStartDate.setText(dateFormat.format(dateStart));
        etStartDate.setOnClickListener(this);

        dateFinish = new Date();
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
        adTable = new TableAdapter(act);
        adTable.addItem(new TableRow(getResources().getString(R.string.date),
                getResources().getString(R.string.count_new_pages)));
        adTable.getItem(0).setBold(true);
        String[] mDates = new String[]{"15.02.2015", "16.02.2015", "17.02.2015",
                "18.02.2015", "18.02.2015", "19.02.2015", "20.02.2015"};
        int[] mCount = new int[]{1, 2, 0, 4, 1, 9, 2};
        int k = 0;
        for (int i = 0; i < mDates.length; i++) {
            adTable.addItem(new TableRow(mDates[i], mCount[i]));
            k += mCount[i];
        }
        adTable.addItem(new TableRow(getResources().getString(R.string.total_for_period), k));
        adTable.getItem(adTable.getCount() - 1).setBold(true);
        lvTable.setAdapter(adTable);
    }


    @Override
    public void onClick(View view) {
        DatePickerDialog datePickerDialog;
        if (view.getId() == R.id.etStartDate) {
            datePickerDialog = new DatePickerDialog(act, dateStartListener,
                    dateStart.getYear(), dateStart.getMonth(), dateStart.getDay());
        } else { //if (view.getId() == R.id.etFinishDate)
            datePickerDialog = new DatePickerDialog(act, dateFinishListener,
                    dateFinish.getYear(), dateFinish.getMonth(), dateFinish.getDay());
        }
        Date max = new Date();
        datePickerDialog.getDatePicker().setMaxDate(max.getTime());
        Date min = new Date(getYearForDate(MIN_YEAR), MIN_MONTH, MIN_DAY);
        datePickerDialog.getDatePicker().setMinDate(min.getTime());
        datePickerDialog.show();
    }

    private int getYearForDate(int year) {
        return year - 1900;
    }


}
