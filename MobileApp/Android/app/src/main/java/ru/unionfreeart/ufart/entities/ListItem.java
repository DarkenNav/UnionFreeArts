package ru.unionfreeart.ufart.entities;


/**
 * Created by NeoSvet on 20.04.2017.
 */

public class ListItem {
    public int id;
    public String name;

    public ListItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}