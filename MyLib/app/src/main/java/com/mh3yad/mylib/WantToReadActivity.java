package com.mh3yad.mylib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class WantToReadActivity extends AppCompatActivity {

    RecyclerView wanToReadRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);
        wanToReadRec = findViewById(R.id.wanToReadRec);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"want");
        wanToReadRec.setAdapter(adapter);
        wanToReadRec.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());
    }

}