package com.example.bestyoutube.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class YoutubeVideo implements Parcelable {

    private long id;
    private String title;
    private String description;
    private String url;
    private String categorie;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String title, String description, String url, String categorie) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
    }

    public YoutubeVideo(Parcel parcel) {
        this.id = parcel.readLong();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.url = parcel.readString();
        this.categorie = parcel.readString();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(categorie);
    }

    public static final Parcelable.Creator<YoutubeVideo> CREATOR = new Parcelable.Creator<YoutubeVideo>(){

        @Override
        public YoutubeVideo createFromParcel(Parcel source) {
            return new YoutubeVideo(source);
        }

        @Override
        public YoutubeVideo[] newArray(int size) {
            return new YoutubeVideo[size];
        }
    };
}
