package com.mh3yad.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private Button btnAddCurrentlyReading,btnAddAlreadyRead,btnAddWantToRead,btnAddToFavourite;
    private TextView txtBookDescription,longDesc;
    private EditText txtbookName,txtBookAuthor,txtBookPages;
    private ImageView imageBook;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        overridePendingTransition(R.anim.slide_in,R.anim.sile_out);
        initViews();

        Intent intent = getIntent();
        if(null != intent){

            int  bookid = intent.getIntExtra("bookid",-1);
            Book incomingbook = Utils.getInstance(this).getBookById(bookid);
           setData(incomingbook);
           handleAlreadyRead(incomingbook);
           handleWAntToRead(incomingbook);

        }

    }



    public  void handleWAntToRead(final Book book){
        final ArrayList<Book> wantReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existinWantReadBooks = false;
        for(Book b: wantReadBooks){
            if(b.getId() == book.getId()){
                existinWantReadBooks = true;
                Toast.makeText(BookActivity.this, "addedd already", Toast.LENGTH_SHORT).show();
            }
        }

        if(existinWantReadBooks){
           btnAddWantToRead.setEnabled(false);
            Toast.makeText(BookActivity.this, "addedd already", Toast.LENGTH_SHORT).show();
        }else {
            btnAddWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantRead(book)){
                        Toast.makeText(BookActivity.this, "addedd successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void handleAlreadyRead(final Book book){
        final ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadbooks();
        boolean existinAlreadyReadBok = false;
        for(Book s: alreadyReadBooks) {
            if (s.getId() == book.getId()) {
                existinAlreadyReadBok = true;
                Toast.makeText(BookActivity.this, "addedd already", Toast.LENGTH_SHORT).show();
            }
        }
            if(existinAlreadyReadBok){
                btnAddAlreadyRead.setEnabled(false);
                Toast.makeText(BookActivity.this, "addedd successfully", Toast.LENGTH_SHORT).show();
            }else{
                btnAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                            Toast.makeText(BookActivity.this, "addedd successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BookActivity.this,AlreadyReadActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BookActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    public void setData(Book book){
        txtbookName.setText(book.getName());
        txtBookAuthor.setText(book.getAuthor());
        txtBookPages.setText(String.valueOf(book.getPages()));
        txtBookDescription.setText(book.getLongDec());
        Glide.with(this).
               asBitmap().
               load(book.getImageUrl()).
               into(imageBook);
    }
    public void  initViews(){
        btnAddAlreadyRead = findViewById(R.id.btnAddAlreadyRead);
        btnAddCurrentlyReading = findViewById(R.id.btnAddCurrentlyReading);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite);
        btnAddWantToRead = findViewById(R.id.btnAddWantToRead);
        txtBookDescription = findViewById(R.id.longDesc);
        btnAddAlreadyRead = findViewById(R.id.btnAddAlreadyRead);
        txtbookName = findViewById(R.id.txtbookName);
        txtBookAuthor = findViewById(R.id.txtBookAuthor);
        txtBookPages = findViewById(R.id.txtBookPages);
        imageBook = findViewById(R.id.imageBook);
        longDesc = findViewById(R.id.longDesc);

    }
}