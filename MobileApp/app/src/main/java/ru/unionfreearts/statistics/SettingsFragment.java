package ru.unionfreearts.statistics;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {
    private MainActivity act;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        act = (MainActivity) getActivity();
        act.setTitle(getResources().getString(R.string.settings));

        return inflater.inflate(R.layout.fragment_total, container, false);
    }
}
