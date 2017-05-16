package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.utils.Settings;

public class SettingsFragment extends Fragment {
    private MainActivity activity;
    private View container;
    private EditText etAddress;
    private Settings settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = inflater.inflate(R.layout.fragment_settings, container, false);
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.settings));

        initUI();

        return this.container;
    }

    private void initUI() {
//        etAddress = (EditText)container.findViewById(R.id.etAddress);

//        settings = new Settings(activity);
//        etAddress.setText(settings.getAddress());
    }

    @Override
    public void onPause() {
//        settings.setAddress(etAddress.getText().toString());
        super.onPause();
    }
}
