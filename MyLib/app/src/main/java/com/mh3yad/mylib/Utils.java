package com.mh3yad.mylib;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static  final  String  All_Books_key = "all_books";
    private static  final  String  Alredr_Read_Books_key = "already_read_books";
    private static  final  String  Want_to_read = "want_to_readd";
    private static Utils instance;
    private SharedPreferences sharedPreferences ;

     private  static ArrayList<Book> allbooks;
    private  static  ArrayList<Book> alreadyReadbooks;
    private  static  ArrayList<Book> wantToReadBooks;
    private  static  ArrayList<Book> currentlyReadingBooks;
    private  static  ArrayList<Book> favouriteBooks;

    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
    if(null == getAllbooks())    {
        initData();
    }
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(null == getAlreadyReadbooks()){
            editor.putString(Alredr_Read_Books_key,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getWantToReadBooks()){
            editor.putString(Want_to_read,gson.toJson(new ArrayList<Book>()));
            editor.commit();

        } if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();

        } if(null == favouriteBooks){
            favouriteBooks = new ArrayList<>();

        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,11,"1Q84","https://images-na.ssl-images-amazon.com/images/I/51wYhgDbDnL._SX329_BO1,204,203,200_.jpg","iQ","short","long"));
        books.add(new Book(2,11,"mh3yad","https://pbs.twimg.com/profile_images/1260511303866736640/imUO4pSa_400x400.jpg","me","short","a \ttps://images-na.ssl-images-amazon.com/images/I/51wYhgDbDnL._SX329_BO1,204,203,200_.jpgn \r g"));

        SharedPreferences.Editor editor =  sharedPreferences.edit();
        Gson gson = new Gson();
        String text  = gson.toJson(books);
        editor.putString(All_Books_key,gson.toJson(books));
        editor.commit();
    }


    public static  Utils  getInstance(Context context) {

        if(null!= instance){
            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }
    }

    public  ArrayList<Book> getAllbooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(All_Books_key,null),type);
        return books;
    }

    public  ArrayList<Book> getAlreadyReadbooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(Alredr_Read_Books_key,null),type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(Want_to_read,null),type);
        return books;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllbooks();
        if(null != books){
            for (Book s: books) {
                if(s.getId() == id) {
                    return  s;
                }
            }
        }

        return  null;
    }
    public  boolean addToAlreadyRead(Book book){
        return  alreadyReadbooks.add(book);
    }



    public  boolean addToWantRead(Book book){
        ArrayList<Book> books = getAlreadyReadbooks();
        if(book != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Alredr_Read_Books_key);
                editor.putString(Alredr_Read_Books_key,gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }
    public boolean removeAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadbooks();
        if(null != books){
            for(Book b: books){
                if(b.getId() == book.getId() ){
                    books.remove(b);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor =  sharedPreferences.edit();
                    editor.remove(Alredr_Read_Books_key);
                    editor.putString(Alredr_Read_Books_key,gson.toJson(books));
                    editor.commit();
                    return  true;
                }

            }
        }
        return  false;
    }
}
