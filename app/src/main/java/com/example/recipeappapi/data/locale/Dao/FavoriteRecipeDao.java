package com.example.recipeappapi.data.locale.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.recipeappapi.data.model.FavoriteModel;

import java.util.List;

@Dao
public interface  FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addData(FavoriteModel favoriteModel);

    @Query("select * from favoriteTable")
    LiveData<List<FavoriteModel>> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favoriteTable WHERE idFavorite=:id)")
    int isFavorite(int id);

    @Delete
    void delete(FavoriteModel favoriteList);
}
