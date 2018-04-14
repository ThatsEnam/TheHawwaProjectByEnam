package com.movietopper.movietopper.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.movietopper.movietopper.R;
import com.movietopper.movietopper.activity.LoadWebView;
import com.movietopper.movietopper.interfaces.RetrofitRequestAPI;
import com.movietopper.movietopper.model.Category;
import com.movietopper.movietopper.model.Movie;
import com.movietopper.movietopper.singleton.MyRetrofit;

import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Enam on 3/6/2018.
 */

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static String TAG = "Adapter";

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof LatestMovieViewHolder) {
            if (((LatestMovieViewHolder) holder).shimmerFrameLayout.getVisibility() == View.VISIBLE) {
                if (!((LatestMovieViewHolder) holder).shimmerFrameLayout.isAnimationStarted()) {
                    ((LatestMovieViewHolder) holder).shimmerFrameLayout.startShimmerAnimation();

                }
            }
        }

        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        if (holder instanceof LatestMovieViewHolder) {
            if (((LatestMovieViewHolder) holder).shimmerFrameLayout.getVisibility() == View.VISIBLE) {
                if (((LatestMovieViewHolder) holder).shimmerFrameLayout.isAnimationStarted()) {
                    ((LatestMovieViewHolder) holder).shimmerFrameLayout.stopShimmerAnimation();
                    ((LatestMovieViewHolder) holder).shimmerFrameLayout.setAnimation(null);
                    ((LatestMovieViewHolder) holder).shimmerFrameLayout.clearAnimation();
                    ((LatestMovieViewHolder) holder).shimmerFrameLayout.setDrawingCacheEnabled(false);

                }
            }
        }
        super.onViewDetachedFromWindow(holder);
    }

    private Context currentContext;

    public HomeRecycleAdapter(Context c, Context currentContex) {
        Log.d(TAG, "HomeRecycleAdapter: Constructor");
        this.context = c;
        this.currentContext = currentContex;
    }

    /**
     * Called when RecyclerView needs a new {@link @MyViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new MyViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new MyViewHolder will be used to display items of the adapter using
     * {@link #@onBindViewHolder(MyViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new MyViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #@onBindViewHolder(MyViewHolder, int)
     */
    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.section_recyclerview, parent, false);
        View searchView = layoutInflater.inflate(R.layout.search_item, parent, false);

        switch (viewType) {
            case ViewType.IN_THEATER_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.IN_THEATER);
            case ViewType.MOVIES_BY_GENRE:
                return new ByGenreViewHolder(view);
            case ViewType.TELEVISION_SHOW:
                return new LatestMovieViewHolder(view, CategoryID.TV_SERIES);
            case ViewType.HOLLYWOOD_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.HOLLYWOOD);
            case ViewType.BOLLYWOOD_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.BOLLYWOOD);
            case ViewType.ANIMATION_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.ANIMATED);
            case ViewType.ACTION_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.ACTION);
            case ViewType.HORROR_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.HORROR);
            case ViewType.THRILLER_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.THRILLER);
            case ViewType.SEARCH_MOVIE:
                return new SearchViewHolder(searchView);
            case ViewType.REQUESTED_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.REQUESTED);
            case ViewType.SERIES_MOVIE:
                return new LatestMovieViewHolder(view, CategoryID.SERIES);
        }
        return new LatestMovieViewHolder(view, CategoryID.SCI_FI);
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        switch (position) {
            case 0:
                return ViewType.SEARCH_MOVIE;
            case 1:
                return ViewType.IN_THEATER_MOVIE;
            case 2:
                return ViewType.REQUESTED_MOVIE;
            case 3:
                return ViewType.TELEVISION_SHOW;
            case 4:
                return ViewType.BOLLYWOOD_MOVIE;
            case 5:
                return ViewType.HOLLYWOOD_MOVIE;
            case 6:
                return ViewType.ACTION_MOVIE;
            case 7:
                return ViewType.HORROR_MOVIE;
            case 8:
                return ViewType.ANIMATION_MOVIE;
            case 9:
                return ViewType.SCI_FI_MOVIE;
            case 10:
                return ViewType.THRILLER_MOVIE;
            case 11:
                return ViewType.SERIES_MOVIE;
            case 12:
                return ViewType.MOVIES_BY_GENRE;

        }
        return -1;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        switch (holder.getItemViewType()) {
            case ViewType.IN_THEATER_MOVIE:
                LatestMovieViewHolder latestMovieViewHolder = (LatestMovieViewHolder) holder;
                latestMovieViewHolder.sectionTitle.setText("Latest");
                break;

            case ViewType.MOVIES_BY_GENRE:
                ByGenreViewHolder byGenreViewHolder = (ByGenreViewHolder) holder;
                byGenreViewHolder.titleMovie.setText("Genre");
                break;
            case ViewType.TELEVISION_SHOW:
                LatestMovieViewHolder tvShowViewHolder = (LatestMovieViewHolder) holder;
                tvShowViewHolder.sectionTitle.setText("TV Shows");
                break;
            case ViewType.HOLLYWOOD_MOVIE:
                LatestMovieViewHolder hollywoodViewHolder = (LatestMovieViewHolder) holder;
                hollywoodViewHolder.sectionTitle.setText("Hollywood");
                break;
            case ViewType.BOLLYWOOD_MOVIE:
                LatestMovieViewHolder bollywoodViewHolder = (LatestMovieViewHolder) holder;
                bollywoodViewHolder.sectionTitle.setText("Bollywood");

                break;
            case ViewType.ANIMATION_MOVIE:
                LatestMovieViewHolder animationHolder = (LatestMovieViewHolder) holder;
                animationHolder.sectionTitle.setText("Animation");
                break;
            case ViewType.ACTION_MOVIE:
                LatestMovieViewHolder actionHolder = (LatestMovieViewHolder) holder;
                actionHolder.sectionTitle.setText("Action");
                break;
            case ViewType.HORROR_MOVIE:
                LatestMovieViewHolder horrorholder = (LatestMovieViewHolder) holder;
                horrorholder.sectionTitle.setText("Horror");
                break;
            case ViewType.THRILLER_MOVIE:
                LatestMovieViewHolder thrillerholder = (LatestMovieViewHolder) holder;
                thrillerholder.sectionTitle.setText("Thriller");
                break;
            case ViewType.SCI_FI_MOVIE:
                LatestMovieViewHolder scifi = (LatestMovieViewHolder) holder;
                scifi.sectionTitle.setText("Sci_Fi");
                break;
            case ViewType.REQUESTED_MOVIE:
                LatestMovieViewHolder requesteHolder = (LatestMovieViewHolder) holder;
                requesteHolder.sectionTitle.setText("Requested");
                break;
            case ViewType.SERIES_MOVIE:
                LatestMovieViewHolder series = (LatestMovieViewHolder) holder;
                series.sectionTitle.setText("Series");
                break;
            case ViewType.SEARCH_MOVIE:
                ((SearchViewHolder) holder).searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String searchString) {
                        //   Toast.makeText(context, URLEncoder.encode(s, "utf-8"), Toast.LENGTH_SHORT).show();
                        currentContext.startActivity(new Intent(currentContext, LoadWebView.class).
                                putExtra("url", "http://download.movietopper.net/?s=" + URLEncoder.encode(searchString)));

                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                });

        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 13;
    }


    /**
     * @ MyViewHolder Starts Here.
     */

    class LatestMovieViewHolder extends RecyclerView.ViewHolder {
        RecyclerView sectionRecylerView;
        TextView sectionTitle;
        ShimmerFrameLayout shimmerFrameLayout = null;
        ViewGroup viewGroup;
        int categoryID;


        LatestMovieViewHolder(View itemView, int categoryId) {
            super(itemView);
            this.sectionRecylerView = itemView.findViewById(R.id.singleSectionRecylerView);
            this.sectionTitle = itemView.findViewById(R.id.sectionTitle);
            this.sectionTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/robotothin.ttf"));
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerView);
            viewGroup = itemView.findViewById(R.id.sectionWrapper);
            this.categoryID = categoryId;
            configureInnerRecyclerView();
        }

        private void configureInnerRecyclerView() {
            this.shimmerFrameLayout.setVisibility(View.VISIBLE);
            this.shimmerFrameLayout.setWillNotCacheDrawing(true);
            this.sectionRecylerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false));
            this.sectionRecylerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            /*final MySingleItemClickListener listener = new MySingleItemClickListener() {
                @Override
                public void onItemClick(int position, String url) {
                    Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
                    currentContext.startActivity(new Intent(currentContext, LoadWebView.class).putExtra("url", url));

                }
            };*/
            RetrofitRequestAPI retrofitRequestAPI = MyRetrofit.getRetrofitInstance().create(RetrofitRequestAPI.class);
            Call<List<Movie>> requestObject = retrofitRequestAPI.getMoviesInTheater(this.categoryID);
            requestObject.enqueue(new Callback<List<Movie>>() {
                @Override
                public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                    if (response.body() != null) {
                        if (shimmerFrameLayout.isAnimationStarted())
                            shimmerFrameLayout.stopShimmerAnimation();
                        sectionRecylerView.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.GONE);
                        sectionRecylerView.setAdapter(new SectionAdapter(context, response.body(), currentContext));


                    }
                }

                @Override
                public void onFailure(Call<List<Movie>> call, Throwable t) {
                    //  Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    class ByGenreViewHolder extends RecyclerView.ViewHolder {
        RecyclerView sectionRecylerView;
        TextView titleMovie;

        LinearLayout recyclerPlaceHolder;

        ByGenreViewHolder(View itemView) {
            super(itemView);
            this.sectionRecylerView = itemView.findViewById(R.id.singleSectionRecylerView);
            this.titleMovie = itemView.findViewById(R.id.sectionTitle);
            this.recyclerPlaceHolder = itemView.findViewById(R.id.genrePlaceHolder);
            this.sectionRecylerView.setLayoutManager(new GridLayoutManager(context, 2));
            this.titleMovie.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/robotothin.ttf"));

            /*New Code Goes Here*/
            this.recyclerPlaceHolder.setVisibility(View.VISIBLE);

            Retrofit retrofitInstance = MyRetrofit.getRetrofitInstance();
            RetrofitRequestAPI retrofitRequestAPI = retrofitInstance.create(RetrofitRequestAPI.class);
            Call<List<Category>> listOfCategory = retrofitRequestAPI.getCategories(12);

            listOfCategory.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    if (response.body() != null) {
                        recyclerPlaceHolder.setVisibility(View.GONE);
                        sectionRecylerView.setAdapter(new CategoryAdapter(response.body(), context, currentContext));
                        Log.w(TAG, "onResponse: ");
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());

                }
            });


        }


    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        SearchView searchMovie;
        View searchPlat;

        SearchViewHolder(View itemView) {
            super(itemView);
            searchMovie = itemView.findViewById(R.id.searchView);
            int platid = context.getResources().getIdentifier("android:id/search_plate", null, null);

            searchPlat = searchMovie.findViewById(platid);
            if (searchPlat != null) {
                searchPlat.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            }
        }
    }
    /**@ MyViewHolder Ends Here.*/
}

class ViewType {
    final static int IN_THEATER_MOVIE = 0;
    final static int MOVIES_BY_GENRE = 1;
    final static int TELEVISION_SHOW = 2;
    final static int HOLLYWOOD_MOVIE = 3;
    final static int BOLLYWOOD_MOVIE = 4;
    final static int ANIMATION_MOVIE = 5;
    final static int ACTION_MOVIE = 6;
    final static int HORROR_MOVIE = 7;
    final static int THRILLER_MOVIE = 8;
    final static int SCI_FI_MOVIE = 9;
    final static int SEARCH_MOVIE = 11;
    final static int REQUESTED_MOVIE = 12;
    final static int SERIES_MOVIE = 13;
}

class CategoryID {
    final static int IN_THEATER = 25493;
    final static int HOLLYWOOD = 25360;
    final static int BOLLYWOOD = 25073;
    final static int TV_SERIES = 27940;
    final static int ANIMATED = 28017;
    final static int ACTION = 25573;
    final static int HORROR = 27943;
    final static int THRILLER = 25015;
    final static int SCI_FI = 25631;
    final static int REQUESTED = 30547;
    final static int SERIES = 39335;
}

