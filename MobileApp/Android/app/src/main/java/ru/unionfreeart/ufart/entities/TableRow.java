package ru.unionfreeart.ufart.entities;

/**
 * Created by NeoSvet on 16.04.2017.
 */

public class TableRow {
    public String name = null, rank;
    public long date;
    private boolean bold;

    public TableRow(String name, String value) {
        this.name = name;
        rank = value;
    }

    public TableRow(String name, int value) {
        this.name = name;
        rank = String.valueOf(value);
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
        return rank;
    }

    public long getDate() {
        return date;
    }
}
