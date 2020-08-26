package com.mh3yad.mylib;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button btnAllBooks,btnCurrentlyReading,btnAlreadyRead,btnWantToRead,btnFavourite,btnAbout,wishlist;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });
         Utils.getInstance(this);

         btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this,AlreadyReadActivity.class);
                 startActivity(intent);
             }
         });
         btnWantToRead.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }


         });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AlreadyReadActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Developed by mohamed ayad aka (mh3yad) \n would you like to visit our site.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Link", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent  = new Intent(MainActivity.this, WebsiteActivity.class);
                                startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "Dismiss",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }

    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnAddCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnFavourites);
        btnFavourite = findViewById(R.id.btnFavourites);
        btnAbout = findViewById(R.id.btnAbout);
        wishlist = findViewById(R.id.wishlist);
    }

}
/// TODO: 8/20/2020 gradle glide dependency