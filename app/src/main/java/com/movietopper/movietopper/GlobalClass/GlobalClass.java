package com.movietopper.movietopper.GlobalClass;

import android.support.v7.app.ActionBar;

/**
 * Created by Enam on 4/11/2018.
 */

public class GlobalClass {
    public static void toolbarConstrutor(ActionBar actionBar, String toolbarTitle) {
        actionBar.setTitle(toolbarTitle);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
