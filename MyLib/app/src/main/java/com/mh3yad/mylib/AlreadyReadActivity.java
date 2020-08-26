package com.mh3yad.mylib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlreadyReadActivity extends AppCompatActivity {

    private TextView btnDelete;
    private RecyclerView bookRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);
        btnDelete = findViewById(R.id.btnDelete);
        overridePendingTransition(R.anim.slide_in,R.anim.sile_out);

        bookRecView  = findViewById(R.id.bookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"alreadyRead");
        bookRecView.setAdapter(adapter);
        bookRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getAlreadyReadbooks());
    }

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(this,MainActivity.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);

    }

}