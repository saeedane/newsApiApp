package com.example.recipeappapi.data.locale;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.recipeappapi.data.locale.Dao.FavoriteRecipeDao;
import com.example.recipeappapi.data.model.FavoriteModel;

@Database(entities = {FavoriteModel.class}, version = 3, exportSchema = false)

public abstract class DatabaseRecipeFavorite extends RoomDatabase {

    /**
     * Instance of this class for Singleton
     */
    private static final Object LOCK = new Object();

    /**
     * Database file name
     */
    private static final String DATABASE_NAME = "recipe_fav_db";
    /**
     * Instance of this class for Singleton
     */
    private static DatabaseRecipeFavorite sInstance;

    /**
     * Method used to get an instance of AppDatabase class
     *
     * @param context Context to use for Room initializations
     * @return an instance of AppDatabase class
     */
    public static DatabaseRecipeFavorite getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        DatabaseRecipeFavorite.class,
                        DatabaseRecipeFavorite.DATABASE_NAME

                ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }
        }
        return sInstance;
    }

    /**
     * Return object of WeatherInfoDao to read, write, delete and update weather info
     * @return an instance of FavoriteRecipeDao
     */



    public abstract FavoriteRecipeDao favoriteDao();
}
