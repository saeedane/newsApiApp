package com.example.recipeappapi.data.model.response;

import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.RecipeModel;

import java.util.List;

public class ResponseLastNews {
    private int status ;
    private List<RecipeModel> news;

    public int getStatus() {
        return status;
    }

    public List<RecipeModel> getLastNewsModels() {
        return news;
    }
}
