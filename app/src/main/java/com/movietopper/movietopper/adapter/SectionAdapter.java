package com.movietopper.movietopper.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.movietopper.movietopper.R;
import com.movietopper.movietopper.activity.LoadWebView;
import com.movietopper.movietopper.interfaces.MySingleItemClickListener;
import com.movietopper.movietopper.model.Movie;

import java.util.List;

/**
 * Created by Enam on 3/31/2018.
 */

class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> implements MySingleItemClickListener {
    List<Movie> movieList;
    Context latestMovieContext;
    Context currentContext;


    SectionAdapter(Context context, List<Movie> movieList, Context currentContext) {
        this.currentContext = currentContext;
        this.movieList = movieList;
        this.latestMovieContext = context;

    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movieTitle.setText(movieList.get(position).getTitle());
        //Toast.makeText(latestMovieContext, movieList.get(position).getMovieUrl().replace("'\'", ""), Toast.LENGTH_LONG).show();
        Glide.with(latestMovieContext)
                .load(movieList.get(position).getThumnailUrl().replace("'\'", ""))
                .apply(new RequestOptions().override(120, 180))
                .into(holder.thumnail);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(holder.getAdapterPosition(), movieList
                        .get(holder.getAdapterPosition())
                        .getMovieUrl(), movieList
                        .get(holder.getAdapterPosition()));
               // Toast.makeText(currentContext,"Fucked",Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onItemClick(int position, String url, Movie movie) {
        currentContext.startActivity(new Intent(currentContext, LoadWebView.class).putExtra("url", url).putExtra("parcel", movie));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout linearLayout;
        ImageView thumnail = null;
        TextView movieTitle = null;
        ImageView overlayRipple = null;


        ViewHolder(View itemView) {
            super(itemView);
            this.thumnail = itemView.findViewById(R.id.posterImageView);
            this.movieTitle = itemView.findViewById(R.id.titleTextView);
            this.linearLayout = itemView.findViewById(R.id.singleMovieWrapper);
           /* this.overlayRipple = itemView.findViewById(R.id.imageOverlay);
            this.overlayRipple.bringToFront();*/
            Log.d("sdfsdfsdfsdf", "ViewHolder: Fucked");
        }
    }


}