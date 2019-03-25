package com.xheghun.theauthor.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksApi {

    @GET("/books/v1/volumes")
    Call<List<Example>> getBooks(@Query("q") String query, @Query("fields") String field);
}