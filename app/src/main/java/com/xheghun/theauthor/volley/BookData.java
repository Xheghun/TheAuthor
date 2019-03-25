package com.xheghun.theauthor.volley;

public class BookData {

/*
    Context context;
    ArrayList<BookInfo> books = new ArrayList<>();

    public BookData(Context context, String query) {
        this.context = context;
        this.mQuery = query;
    }

    private String mQuery;
     Base URL for Books API.

    public ArrayList<BookInfo> getBooks() {
        String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q="+mQuery;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BOOK_BASE_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            int count = 0;
                            JSONObject jsonObject = new JSONObject();
                            JSONArray items = jsonObject.getJSONArray("items");
                            while (count < items.length()) {
                                try {
                                    JSONObject book = items.getJSONObject(count);
                                    JSONObject volumeInfo= book.getJSONObject("volumeInfo");


                                    String title = volumeInfo.getString("title");
                                    String authors = volumeInfo.getString("authors");
                                    String publisher = volumeInfo.getString("publisher");
                                    String publishedDate = volumeInfo.getString("publishedDate");
                                    String description = volumeInfo.getString("description");
                                    int pageCount = volumeInfo.getInt("pageCount");
                                    String categories = volumeInfo.getString("categories");
                                    String language = volumeInfo.getString("language");
                                    String thumbnail = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");


                                    BookInfo bookInfo = new BookInfo(title, authors
                                            , publisher, publishedDate,
                                            description, pageCount, categories,
                                            language, thumbnail);
                                    books.add(bookInfo);
                                    count++;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return books;
    }*/
}
