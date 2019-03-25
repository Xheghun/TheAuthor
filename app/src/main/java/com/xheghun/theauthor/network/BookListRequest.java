package com.xheghun.theauthor.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

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
       Call<List<Example>> call = booksApi.getBooks(mQuery, "items/volumeInfo");
       Log.v("QUERY", mQuery + "  is the query");
       call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Example> books = response.body();
                for (Example book : books) {

                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}