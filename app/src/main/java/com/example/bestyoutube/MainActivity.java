package com.example.bestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bestyoutube.adapter.YoutubeVideoAdapter;
import com.example.bestyoutube.dao.YoutubeVideoDao;
import com.example.bestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //CONSTANTS
    private final String TAG = "YoutubeVideoApplacation";
    public final String YOUTUBEVIDEO_KEY = "YOUTUBEVIDEO";

    //Views
    private TextView tvVideo;
    private RecyclerView rvVideo;

    //BDD
    private YoutubeVideoDao youtubeVideoDao;
    private String youtubeVideoBdd;

    //OTHER
    private Context context;
    private YoutubeVideoAdapter youtubeVideoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);

        rvVideo = findViewById(R.id.rvVideo);

        // create context
        context = getApplicationContext();
        //instanciate video DAO
        youtubeVideoDao = new YoutubeVideoDao(context);
        // Créer le layoutManager. Au choix LinearLayoutManager ou GridLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvVideo.setHasFixedSize(true);
        // affecte au RecyclerView son LayoutManager
        rvVideo.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");

        YoutubeVideoAsyncTasks toDoAsyncTasks  = new YoutubeVideoAsyncTasks();
        toDoAsyncTasks.execute();
    }

    public class YoutubeVideoAsyncTasks extends AsyncTask<String, String, List<YoutubeVideo>> {

        @Override
        protected List<YoutubeVideo> doInBackground(String... strings) {

            List<YoutubeVideo> responseTodo = new ArrayList<>();

            try {
                responseTodo = youtubeVideoDao.list();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseTodo;
        }

        @Override
        protected void onPostExecute(List<YoutubeVideo> youtubeVideos) {

            youtubeVideoAdapter = new YoutubeVideoAdapter(youtubeVideos, new YoutubeVideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(YoutubeVideo youtubeVideo) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(YOUTUBEVIDEO_KEY, youtubeVideo);
                    startActivity(intent);
                }
            });
            rvVideo.setAdapter(youtubeVideoAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        // effectue une action suivant l'item sélectionné
        // on test avec un switch l'id de l'item
        switch (item.getItemId()){
            case R.id.itmAdd:
                // Créer un Intent pour ouvrir la page Add
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}