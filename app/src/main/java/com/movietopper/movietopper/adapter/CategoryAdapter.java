package com.movietopper.movietopper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movietopper.movietopper.R;
import com.movietopper.movietopper.activity.LoadWebView;
import com.movietopper.movietopper.model.Category;

import java.util.List;

/**
 * Created by Enam on 4/1/2018.
 */

class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<Category> listOfCategory;
    private Typeface thinFont;
    Context context, currentContext;

    CategoryAdapter(List<Category> body, Context context, Context currentContext) {
        this.listOfCategory = body;
        this.context = context;
        this.currentContext = currentContext;
        Log.w("Bal", "CategoryAdapter: Hawwa" + body.size());
        thinFont = Typeface.createFromAsset(context.getAssets(), "fonts/robotothin.ttf");
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.by_genre_item, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
       /* Random rnd = new Random();
        holder.genreWrapper.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));*/
        holder.genreTitle.setText(listOfCategory.get(position).getNameCategory());
        holder.movieCount.setText(String.format("%03d", Integer.parseInt(listOfCategory.get(position).getNumberOfMovie())));
        holder.genreWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentContext.startActivity(new Intent(currentContext, LoadWebView.class).putExtra("url", listOfCategory.get(holder.getAdapterPosition()).getUrlCategory()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfCategory.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView genreTitle;
        LinearLayout genreWrapper;
        TextView movieCount;

        MyViewHolder(View itemView) {
            super(itemView);
            this.genreTitle = itemView.findViewById(R.id.genreTitleTextView);
            this.genreWrapper = itemView.findViewById(R.id.genreWrapper);
            this.movieCount = itemView.findViewById(R.id.movieCount);
            this.genreTitle.setTypeface(thinFont);
            this.movieCount.setTypeface(thinFont);


        }
    }
}