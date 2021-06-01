package com.example.bestyoutube;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bestyoutube.dao.YoutubeVideoDao;
import com.example.bestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Context context;
    private EditText edtTitle;
    private EditText edtUrl;
    private EditText edtDescription;
    private Spinner spnCategory;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        context = getApplicationContext();

        // récupère la barre d'action
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get reference of widgets from XML layout
        edtTitle = findViewById(R.id.edtTitle);
        edtUrl = findViewById(R.id.edtUrl);
        edtDescription = findViewById(R.id.edtDescription);
        spnCategory = findViewById(R.id.spnCategory);
        btnAdd = findViewById(R.id.btnAdd);

        // Initializing a String Array
        String[] categories = new String[]{
                "Comedy",
                "Horror",
                "Other",
                "Nul"
        };

        final List<String> categoriesList = new ArrayList<>(Arrays.asList(categories));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,categoriesList
        );

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnCategory.setAdapter(spinnerArrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String url = edtUrl.getText().toString();
                String description = edtDescription.getText().toString();
                String category = spnCategory.getSelectedItem().toString();

                YoutubeVideoDao YoutubeVideoDAO = new YoutubeVideoDao(context);
                YoutubeVideo YoutubeVideo = new YoutubeVideo();
                YoutubeVideo.setTitle(title);
                YoutubeVideo.setCategorie(category);
                YoutubeVideo.setUrl(url);
                YoutubeVideo.setDescription(description);

                YoutubeVideoDAO.add(YoutubeVideo);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        // termine une activité
        finish();
        return super.onSupportNavigateUp();
    }
}