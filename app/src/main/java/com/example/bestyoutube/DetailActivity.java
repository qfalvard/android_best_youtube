package com.example.bestyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bestyoutube.adapter.YoutubeVideoAdapter;
import com.example.bestyoutube.dao.YoutubeVideoDao;
import com.example.bestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    //CONSTANTS
    private final String YOUTUBEVIDEO_KEY = "YOUTUBEVIDEO";

    //Views
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvUrl;
    private TextView tvCategory;
    private YoutubeVideo youtubeVideo;

    private String url;
    private String description;
    private String title;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvCategory = findViewById(R.id.tvCategory);
        tvUrl = findViewById(R.id.tvUrl);
        tvDescription = findViewById(R.id.tvDescription);

        youtubeVideo = getIntent().getParcelableExtra(YOUTUBEVIDEO_KEY);

        title = youtubeVideo.getTitle();
        url = youtubeVideo.getUrl();
        category = youtubeVideo.getCategorie();
        description = youtubeVideo.getDescription();
    }

    @Override
    protected void onStart() {
        super.onStart();

        tvCategory.setText(category);
        tvTitle.setText(title);
        tvUrl.setText(url);
        tvDescription.setText(description);
    }
}