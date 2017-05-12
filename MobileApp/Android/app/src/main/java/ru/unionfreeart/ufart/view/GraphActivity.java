package ru.unionfreeart.ufart.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.repositories.TableRepositories;

public class GraphActivity extends AppCompatActivity {
    private static final String TABLE_NAME = "name";
    private List<String> title = new ArrayList<String>();
    private DataPoint[] list;

    public static void open(Context context, String table) {
        Intent intent = new Intent(context, GraphActivity.class);
        intent.putExtra(TABLE_NAME, table);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        loadTable();
        initGraph();
    }

    private void initGraph() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(list);
        series.setSpacing(0);
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    int i = (int) value;
                    if (value - i == 0d && i < title.size() && i > 0)
                        return title.get(i);
                    else
                        return "";
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
    }

    private void loadTable() {
        TableRepositories table = new TableRepositories(GraphActivity.this,
                getIntent().getStringExtra(TABLE_NAME));
        table.loadTable();

        list = new DataPoint[table.getCount() + 6];
        for (int i = 0; i < table.getCount(); i++) {
            title.add(table.getName(i));
            list[i] = new DataPoint(i, i * 100 + Integer.parseInt(table.getValue(i)));
        }
    }
}
