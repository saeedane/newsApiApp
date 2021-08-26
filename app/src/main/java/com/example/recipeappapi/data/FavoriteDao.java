package com.example.recipeappapi.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recipeappapi.data.model.ItemRecipe;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    public void addData(ItemRecipe favoriteList);

    @Query("select * from favorite")
    public List<ItemRecipe> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favorite WHERE id=:id)")
    public int isFavorite(int id);

    @Delete
    public void delete(ItemRecipe favoriteList);
}
