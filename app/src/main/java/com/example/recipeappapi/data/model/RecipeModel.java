package com.example.recipeappapi.data.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public  class RecipeModel  implements Parcelable  {
    @SerializedName("id")
    private int content_id;
    @SerializedName("title")
    private String content_title;
    @SerializedName("posts_url")
    private String content_image;
    @SerializedName("posts_content")
    private String content_body;
    @SerializedName("youtube_video_url")
    private String video_url;
    @SerializedName("category_name")
    private  String news_category_name;
    @SerializedName("posts_date")
    private String date;



    public RecipeModel() {
    }


    protected RecipeModel(Parcel in) {
        content_id = in.readInt();
        content_title = in.readString();
        content_image = in.readString();
        content_body = in.readString();
        video_url = in.readString();
        news_category_name = in.readString();
        date = in.readString();
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(content_id);
        parcel.writeString(content_title);
        parcel.writeString(content_image);
        parcel.writeString(content_body);
        parcel.writeString(video_url);
        parcel.writeString(news_category_name);
        parcel.writeString(date);
    }


    public int getContent_id() {
        return content_id;
    }

    public String getContent_title() {
        return content_title;
    }


    public String getContent_image() {
        return content_image;
    }

    public String getContent_body() {
        return content_body;
    }

    public String getVideo_url() {
        return video_url;
    }
    public String getNews_category_name() {
        return news_category_name;
    }

    public String getDate() {
        return date;
    }
}
