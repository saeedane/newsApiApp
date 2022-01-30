package com.example.recipeappapi.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="favoriteTable")
public class FavoriteModel {
    @PrimaryKey
    private int idFavorite;
    private String titleFavorite;
    private String imageFavorite;
    private String contentFavorite;



    public String getTitleFavorite() {
        return titleFavorite;
    }

    public void setTitleFavorite(String titleFavorite) {
        this.titleFavorite = titleFavorite;
    }

    public String getImageFavorite() {
        return imageFavorite;
    }

    public void setImageFavorite(String imageFavorite) {
        this.imageFavorite = imageFavorite;
    }

    public int getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(int idFavorite) {
        this.idFavorite = idFavorite;
    }

    public String getContentFavorite() {
        return contentFavorite;
    }

    public void setContentFavorite(String contentFavorite) {
        this.contentFavorite = contentFavorite;
    }
}
