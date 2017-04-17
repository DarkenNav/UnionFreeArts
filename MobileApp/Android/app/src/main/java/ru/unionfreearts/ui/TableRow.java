package ru.unionfreearts.ui;

/**
 * Created by NeoSvet on 16.04.2017.
 */

public class TableRow {
    private String name, value;
    private boolean bold;

    public TableRow(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public TableRow(String name, int value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
