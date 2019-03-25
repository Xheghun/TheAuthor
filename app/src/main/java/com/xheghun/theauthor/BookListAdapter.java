package com.xheghun.theauthor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.xheghun.theauthor.network.Example;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookListAdapter extends RecyclerView.Adapter<BookViewHolder>{
    private Context context;
    private List<Example> list;

    public BookListAdapter(Context context, List<Example> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_result_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        if (list != null) {
            Example book = list.get(position);
            holder.bookAuthor.setText(book.getVolumeInfo().getAuthors().toString());
            holder.bookTitle.setText(book.getVolumeInfo().getTitle());
            Glide.with(context).load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(holder.bookImage);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class BookViewHolder extends RecyclerView.ViewHolder {
    ImageView bookImage;
    TextView bookAuthor;
    TextView bookTitle;
    MaterialButton viewMoreButton;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        bookImage = itemView.findViewById(R.id.book_image);
        bookAuthor = itemView.findViewById(R.id.book_author);
        bookTitle = itemView.findViewById(R.id.book_title);
        viewMoreButton = itemView.findViewById(R.id.view_more);
    }
}