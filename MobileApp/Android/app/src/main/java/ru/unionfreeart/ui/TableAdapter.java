package ru.unionfreeart.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.unionfreeart.ufart.R;

/**
 * Created by NeoSvet on 16.04.2017.
 */

public class TableAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TableRow> data = new ArrayList<TableRow>();

    public TableAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(TableRow item) {
        data.add(item);
    }

    @Override
    public int getCount() {
        return (data == null ? 0 : data.size());
    }

    @Override
    public TableRow getItem(int i) {
        return (data == null || i >= data.size()) ? null : data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (data.get(position).isBold()) {
            convertView = inflater.inflate(R.layout.table_row_bold, null);
        } else {
            convertView = inflater.inflate(R.layout.table_row, null);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.nameColumn);
        tvName.setText(data.get(position).getName());
        TextView tvValue = (TextView) convertView.findViewById(R.id.valueColumn);
        tvValue.setText(data.get(position).getValue());
        return convertView;
    }
}