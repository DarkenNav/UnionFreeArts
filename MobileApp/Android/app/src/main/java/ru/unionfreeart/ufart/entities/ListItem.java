package ru.unionfreeart.ufart.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NeoSvet on 20.04.2017.
 */

public class ListItem {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
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
