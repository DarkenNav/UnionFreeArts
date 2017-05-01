package ru.unionfreeart.ufart.entities;

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
 * Created by NeoSvet on 20.04.2017.
 */

public class ListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ListItem> data = new ArrayList<ListItem>();

    public ListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ListItem item) {
        data.add(item);
    }

    @Override
    public int getCount() {
        return (data == null ? 0 : data.size());
    }

    @Override
    public ListItem getItem(int i) {
        return (i >= data.size()) ? null : data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public int getId(int i) {
        return (i >= data.size()) ? 0 : data.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        tvName.setText(data.get(position).getName());
        return convertView;
    }
}
