package com.movietopper.movietopper.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.movietopper.movietopper.R;
import com.movietopper.movietopper.model.Movie;


public class LoadWebView extends AppCompatActivity {

    WebView myWebView;
    Toolbar toolbar;
    private String URL_TO_LOAD = null;
    private NestedScrollView nestedScrollView = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Movie movieModel = null;


    private void collectData() {
        if (getIntent().getStringExtra("url") != null) {
            URL_TO_LOAD = getIntent().getStringExtra("url");
        } else URL_TO_LOAD = "http://download.movietopper.net";
        if (getIntent().getParcelableExtra("parcel") != null) {
            movieModel = getIntent().getParcelableExtra("parcel");
        }
    }

    private void bindView() {
        myWebView = findViewById(R.id.globalWebView);
        toolbar = findViewById(R.id.customToolbar1);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void bindData() {
        setSupportActionBar(toolbar);
        myWebView.getSettings().setJavaScriptEnabled(true);
        //   myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.setBackgroundColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {

            myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                nestedScrollView.scrollTo(0, 0);
                swipeRefreshLayout.setRefreshing(true);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                getSupportActionBar().setTitle(view.getTitle());
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });
        myWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String downloadUrl, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Intent downloadIntent = new Intent(Intent.ACTION_VIEW);
                downloadIntent.setData(Uri.parse(downloadUrl));
                startActivity(downloadIntent);
            }

        });
        myWebView.loadUrl(URL_TO_LOAD);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myWebView.loadUrl(myWebView.getUrl());
            }
        });

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_webview);
        collectData();
        bindView();
        bindData();
        initActionbar();


    }

    void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (movieModel != null) {
            actionBar.setTitle(movieModel.getTitle());
            actionBar.setSubtitle("on " + movieModel.getReleaseDate());
            /*Toast.makeText(this, movieModel.getTitle() + "\n" + movieModel.getId() + "\n" +
                    movieModel.getThumnailUrl() + "\n" + movieModel.getMovieUrl() + movieModel.getReleaseDate(), Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
        } else if (item.getItemId() == R.id.bookmark) {
            Toast.makeText(getApplicationContext(), "Bookmarks", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.share) {
            Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.goToHome) {
            startActivity(new Intent(this, MainActivity.class));

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

