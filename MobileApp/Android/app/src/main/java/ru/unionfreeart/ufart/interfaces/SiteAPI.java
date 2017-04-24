package ru.unionfreeart.ufart.interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import ru.unionfreeart.ufart.entities.ListItem;

/**
 * Created by NeoSvet on 24.04.2017.
 */

public interface SiteAPI {
    @GET("/site/get")
    void get(
//            @Header(APIConstants.TOKEN_KEY) String token,
            @Query("id") String id,
            ListItem callback
    );
    @GET("/site/getAll")
    void getAll(
//            @Header(APIConstants.TOKEN_KEY) String token,
            Callback<ListItem> callback
    );
    @GET("/site/add")
    void add(
//            @Header(APIConstants.TOKEN_KEY) String token,
            @Query("name") String name,
            ListItem callback
    );
    @GET("/site/update")
    void update(
//            @Header(APIConstants.TOKEN_KEY) String token,
            @Query("id") String id,
            @Query("name") String name,
            ListItem callback
    );
    @GET("/site/remove")
    void remove(
//            @Header(APIConstants.TOKEN_KEY) String token,
            @Query("id") String id,
            boolean callback
    );
}
