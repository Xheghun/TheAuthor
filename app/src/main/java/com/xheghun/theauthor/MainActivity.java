package com.xheghun.theauthor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    TextInputEditText queryText;
    TextInputLayout queryTextLayout;
    MaterialButton searchButton;
    TextView searchResultText;

    boolean visible;
    private ArrayList<BookInfo> bookList;
    private String query;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup container = findViewById(R.id.main_activity_container);
        searchResultText = findViewById(R.id.th_search_result);
        queryText = findViewById(R.id.query);
        queryTextLayout = findViewById(R.id.query_layout);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (isValid(queryText.getText())) {
                    String query = String.valueOf(queryText.getText());
                       BookListRequest request = new BookListRequest(MainActivity.this,query);
                       request.makeRequest();
                }
            }
        });


    }

   /* private void sendQuery(ViewGroup container, String query) {
        BookData bookData = new BookData(MainActivity.this, query);
        bookList =  bookData.getBooks();
        TransitionManager.beginDelayedTransition(container);
        visible = !visible;
        searchResultText.setVisibility(visible ? View.VISIBLE : View.GONE);
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView = findViewById(R.id.th_book_rc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new BookListAdapter(MainActivity.this, bookList));
        recyclerView.setLayoutManager(layoutManager);
    }
*/
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