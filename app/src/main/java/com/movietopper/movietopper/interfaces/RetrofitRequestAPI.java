package com.movietopper.movietopper.interfaces;

import com.movietopper.movietopper.model.Category;
import com.movietopper.movietopper.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Enam on 3/30/2018.
 */

public interface RetrofitRequestAPI {

    @GET("/app/api/v1/get-movies.php/")
    Call<List<Movie>> getMoviesInTheater(@Query("cat_id") int categoryId);

    @GET("/app/api/v1/get-categories.php/")
    Call<List<Category>> getCategories(@Query("limit") int categoryCount);


}
