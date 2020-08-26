package com.mh3yad.mylib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends  RecyclerView.Adapter<BookRecViewAdapter.Viewholder>  {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtName.setText(books.get(position).getName());
        holder.txtAuthor.setText(books.get(position).getAuthor());
        Glide.with(context).
                asBitmap().
                load(books.get(position).getImageUrl()).
                into(holder.imgBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context,BookActivity.class);
              intent.putExtra("bookid",books.get(position).getId());
              context.startActivity(intent);
            }
        });


        if(parentActivity.equals("alreadyRead")){
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    final String a = books.get(position).getName();
                    builder1.setMessage("Are you sure ?.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Utils.getInstance(context).removeAlreadyRead(books.get(position));
                                    Toast.makeText(context, a + " Deleted successfully", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                    Intent intent = new Intent(context,MainActivity.class);
                                    context.startActivity(intent);
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
//                    String a = books.get(position).getName();
//                    Utils.getInstance().removeAlreadyRead(books.get(position));
//                    Toast.makeText(context, a + " Deleted successfully", Toast.LENGTH_SHORT).show();
//                    notifyDataSetChanged();
//                    Intent intent = new Intent(context,MainActivity.class);
//                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private CardView parent;
        private ImageView imgBook;
        private TextView btnDelete;
        private TextView txtName;
        private ImageView btnUpArrow;
        private ImageView btnDownArrow;
        private TextView txtAuthor,txtDescription;
        private RelativeLayout  expandedRelLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);
            btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpArrow = itemView.findViewById(R.id.btnUpArrow);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtShortDesc);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);




            //TODO https://youtu.be/RcSHAkpwXAQ?t=2013
            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expandedRelLayout.setVisibility(View.VISIBLE);
                    btnDownArrow.setVisibility(View.GONE);
                }
            });
            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expandedRelLayout.setVisibility(View.GONE);
                    btnDownArrow.setVisibility(View.VISIBLE);
                }
            });
        }
    }


}
