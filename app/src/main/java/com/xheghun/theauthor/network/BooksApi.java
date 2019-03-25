package com.xheghun.theauthor.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksApi {

    @GET("/books/v1/volumes")
    Call<BookList> getBooks(@Query("q") String query, @Query("fields") String field);
}