package com.xheghun.theauthor.network;

import com.xheghun.theauthor.BookInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksApi {

    @GET("volumes")
    Call<List<BookInfo>> getBooks(@Query("q") String query, @Query("fields") String field);
}