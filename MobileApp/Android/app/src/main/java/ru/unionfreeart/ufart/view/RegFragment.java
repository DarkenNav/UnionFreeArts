package ru.unionfreeart.ufart.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.entities.ListItem;
import ru.unionfreeart.ufart.entities.SpinnerAdapter;

public class RegFragment extends Fragment {
    private final String TYPE_POSITON = "type";
    private MainActivity activity;
    private View container;
    private EditText etLogin, etPassword;
    private Spinner spType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = inflater.inflate(R.layout.fragment_reg, container, false);
        activity = (MainActivity) getActivity();
        activity.setTitle(getResources().getString(R.string.registration));

        initUI();

        restoreActivityState(savedInstanceState);
        return this.container;
    }

    private void restoreActivityState(Bundle state) {
        if (state != null) {
            spType.setSelection(state.getInt(TYPE_POSITON));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(TYPE_POSITON, spType.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }

    private void initUI() {
        spType = (Spinner) container.findViewById(R.id.spType);
        SpinnerAdapter adType = new SpinnerAdapter(activity);
        adType.addItem(new ListItem(0, getResources().getString(R.string.user)));
        adType.addItem(new ListItem(1, getResources().getString(R.string.admin)));
        spType.setAdapter(adType);
        etLogin = (EditText) container.findViewById(R.id.etLogin);
        etPassword = (EditText) container.findViewById(R.id.etPassword);
    }
}
