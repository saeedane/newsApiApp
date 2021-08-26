package com.example.recipeappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.recipeappapi.data.FavoriteDatabase;
import com.example.recipeappapi.data.model.ItemRecipe;

import java.util.ArrayList;

public class favoriteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static FavoriteDatabase favoriteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.recycler);

        favoriteDatabase= Room.databaseBuilder(this,FavoriteDatabase.class,"myfavdb").
                allowMainThreadQueries().build();
        ArrayList<ItemRecipe> favorite = (ArrayList<ItemRecipe>) favoriteDatabase.favoriteDao().getFavoriteData();
        setRecyclerAdapter(favorite);

    }


    private void setRecyclerAdapter(ArrayList<ItemRecipe> favorite){

        RecyclerViewRecipeFavorite recyclerViewRecipe = new RecyclerViewRecipeFavorite(getApplicationContext(),favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(recyclerViewRecipe);

    }
}
