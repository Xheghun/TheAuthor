package com.xheghun.theauthor.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookListRequest {
    Context context;
    public static String mQuery;

    public BookListRequest(Context context, String query) {
        this.context = context;
        mQuery = query;
    }
   public void makeRequest() {

       HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient.Builder okHttp = new OkHttpClient.Builder().addInterceptor(logger);
       String BASE_URL = "https://www.googleapis.com";
       final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
               .client(okHttp.build())
                .build();

        BooksApi booksApi = retrofit.create(BooksApi.class);
       Call<BookList> call = booksApi.getBooks(mQuery, "items/volumeInfo");
       Log.v("QUERY", mQuery + "  is the query");
       call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
                    return;
                }
                BookList books = response.body();
            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}