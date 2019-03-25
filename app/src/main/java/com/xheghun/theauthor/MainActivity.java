package com.xheghun.theauthor;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.xheghun.theauthor.network.BookList;
import com.xheghun.theauthor.network.BooksApi;
import com.xheghun.theauthor.network.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    TextInputEditText queryText;
    TextInputLayout queryTextLayout;
    MaterialButton searchButton;
    TextView searchResultText;
    boolean visible;
    private List<Item> items = null;
    private BookList books;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ViewGroup container = findViewById(R.id.main_activity_container);
        searchResultText = findViewById(R.id.th_search_result);
        queryText = findViewById(R.id.query);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.th_book_rc);
        queryTextLayout = findViewById(R.id.query_layout);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (isValid(queryText.getText())) {
                    visible = !visible;
                    searchResultText.setVisibility(visible ? View.VISIBLE : View.GONE);
                    items = new ArrayList<>();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new BookListAdapter(MainActivity.this, items);
                    recyclerView.setAdapter(adapter);
                    makeRequest();

                }


            }
        });


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
        String mQuery = String.valueOf(queryText.getText());
        Call<BookList> call = booksApi.getBooks(mQuery, "items/volumeInfo");
        Log.v("QUERY", mQuery + "  is the query");
        call.enqueue(new Callback<BookList>() {
            @Override
            public void onResponse(Call<BookList> call, Response<BookList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
                    return;
                }
                books = response.body();
                if (items != null) {
                    items.clear();
                    items.addAll(books.getItems());
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<BookList> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean isValid(Editable text) {
        if (TextUtils.isEmpty(text)) {
            queryTextLayout.setErrorEnabled(true);
            queryTextLayout.setError("this field cannot be empty");
            return false;
        }else {
            queryTextLayout.setError("");
            queryTextLayout.setErrorEnabled(false);
            return true;
        }

    }
}