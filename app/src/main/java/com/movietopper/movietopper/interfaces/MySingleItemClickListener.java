package com.movietopper.movietopper.interfaces;

import com.movietopper.movietopper.model.Movie;

/**
 * Created by Enam on 4/1/2018.
 */

public interface MySingleItemClickListener {
    void onItemClick(int position,String url,Movie movie);
}
