package com.xheghun.theauthor.network;

import android.content.Context;
import android.widget.Toast;

import com.xheghun.theauthor.BookInfo;

import java.util.List;

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
        String BASE_URL = "https://www.googleapis.com/books/v1/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BooksApi booksApi = retrofit.create(BooksApi.class);
        Call<List<BookInfo>> call = booksApi.getBooks(mQuery,"item");
        call.enqueue(new Callback<List<BookInfo>>() {
            @Override
            public void onResponse(Call<List<BookInfo>> call, Response<List<BookInfo>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<BookInfo> books = response.body();
                for (BookInfo book: books) {
                }
            }

            @Override
            public void onFailure(Call<List<BookInfo>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}