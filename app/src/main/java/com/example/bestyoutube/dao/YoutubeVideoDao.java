package com.example.bestyoutube.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bestyoutube.database.YoutubeVideoDbHelper;
import com.example.bestyoutube.pojos.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class YoutubeVideoDao extends Dao {


    public YoutubeVideoDao(Context context) {
        super(new YoutubeVideoDbHelper(context));
    }

    public YoutubeVideo find (int id){

        //déclare une variable qui stockera l'objet créé
        YoutubeVideo YoutubeVideo = null;

        //ouvre la base de données
        open();

        //éxécute la requete et renvoue un Cursor contenant les données
        Cursor cursor = db.rawQuery("select * from " + YoutubeVideoDbHelper.VIDEO_TABLE_NAME +
                        " where " + YoutubeVideoDbHelper.VIDEO_KEY + " = ?",
                new String[] {String.valueOf(id)});

        // positionne le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()){
            YoutubeVideo = new YoutubeVideo();
            YoutubeVideo.setId(cursor.getLong(YoutubeVideoDbHelper.VIDEO_KEY_COLUMN_INDEX));
            YoutubeVideo.setTitle(cursor.getString(YoutubeVideoDbHelper.VIDEO_TITLE_COLUMN_INDEX));
            YoutubeVideo.setDescription(cursor.getString(YoutubeVideoDbHelper.VIDEO_DESCRIPTION_COLUMN_INDEX));
            YoutubeVideo.setUrl(cursor.getString(YoutubeVideoDbHelper.VIDEO_URL_COLUMN_INDEX));
            YoutubeVideo.setCategorie(cursor.getString(YoutubeVideoDbHelper.VIDEO_CATEGORIE_COLUMN_INDEX));
        }

        //ferme la bdd
        close();

        return YoutubeVideo;
    }

    public List<YoutubeVideo> list (){
        //déclare une variable qui stockera la liste d'objets
        List<YoutubeVideo> YoutubeVideos = new ArrayList<>();

        //ouvre la base de données
        open();

        //éxécute la requete et renvoue un Cursor contenant les données
        Cursor cursor = db.rawQuery("select * from " + YoutubeVideoDbHelper.VIDEO_TABLE_NAME, null);

        // positionne le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()){
            // boucle tant que le cursor n'est pas arrivé sur le dernier enregistrement
            while (!cursor.isAfterLast()) {
                YoutubeVideo YoutubeVideo = new YoutubeVideo();
                YoutubeVideo.setId(cursor.getLong(YoutubeVideoDbHelper.VIDEO_KEY_COLUMN_INDEX));
                YoutubeVideo.setTitle(cursor.getString(YoutubeVideoDbHelper.VIDEO_TITLE_COLUMN_INDEX));
                YoutubeVideo.setDescription(cursor.getString(YoutubeVideoDbHelper.VIDEO_DESCRIPTION_COLUMN_INDEX));
                YoutubeVideo.setUrl(cursor.getString(YoutubeVideoDbHelper.VIDEO_URL_COLUMN_INDEX));
                YoutubeVideo.setCategorie(cursor.getString(YoutubeVideoDbHelper.VIDEO_CATEGORIE_COLUMN_INDEX));

                //ajoute le YoutubeVideo créé dans la liste
                YoutubeVideos.add(YoutubeVideo);

                // passe à l'enregisstrement suivant
                cursor.moveToNext();
            }
            // ferme le cursor
            cursor.close();
        }

        //ferme la bdd
        close();

        return YoutubeVideos;
    }

    public void add (YoutubeVideo YoutubeVideo){

        //ouvre la base de données
        open();

        ContentValues values = new ContentValues();

        values.put(YoutubeVideoDbHelper.VIDEO_TITLE, YoutubeVideo.getTitle());
        values.put(YoutubeVideoDbHelper.VIDEO_DESCRIPTION, YoutubeVideo.getDescription());
        values.put(YoutubeVideoDbHelper.VIDEO_URL, YoutubeVideo.getUrl());
        values.put(YoutubeVideoDbHelper.VIDEO_CATEGORIE, YoutubeVideo.getCategorie());

        //effectue une insertion des données et récupère l'id généré
        long id = db.insert(YoutubeVideoDbHelper.VIDEO_TABLE_NAME, null, values);

        // met à jour l'id de l'objet
        YoutubeVideo.setId(id);

        //ferme la bdd
        close();
    }

    public void update (YoutubeVideo YoutubeVideo){

        //ouvre la base de données
        open();

        ContentValues values = new ContentValues();

        values.put(YoutubeVideoDbHelper.VIDEO_TITLE, YoutubeVideo.getTitle());
        values.put(YoutubeVideoDbHelper.VIDEO_DESCRIPTION, YoutubeVideo.getDescription());
        values.put(YoutubeVideoDbHelper.VIDEO_URL, YoutubeVideo.getUrl());
        values.put(YoutubeVideoDbHelper.VIDEO_CATEGORIE, YoutubeVideo.getCategorie());

        //exécute la requete update avec la claure where id = ?
        db.update(YoutubeVideoDbHelper.VIDEO_TABLE_NAME, values, YoutubeVideoDbHelper.VIDEO_KEY + " = ?",
                new String[] {
                        String.valueOf(YoutubeVideo.getId())
                });

        //ferme la bdd
        close();
    }

    public void delete (YoutubeVideo YoutubeVideo){

        //ouvre la base de données
        open();

        //exécute la requete update avec la claure where id = ?
        db.delete(YoutubeVideoDbHelper.VIDEO_TABLE_NAME, YoutubeVideoDbHelper.VIDEO_KEY + " = ?",
                new String[] {
                        String.valueOf(YoutubeVideo.getId())
                });

        //ferme la bdd
        close();
    }
}
