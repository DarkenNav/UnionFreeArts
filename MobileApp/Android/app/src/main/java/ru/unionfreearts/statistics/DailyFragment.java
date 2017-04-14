package ru.unionfreearts.statistics;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DailyFragment extends Fragment {
    private MainActivity act;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        act = (MainActivity) getActivity();
        act.setTitle(getResources().getString(R.string.daily_statistics));

        ListView lvTable = (ListView) container.findViewById(R.id.lvTable);
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }
}
