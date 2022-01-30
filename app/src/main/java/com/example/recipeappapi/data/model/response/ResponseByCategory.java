package com.example.recipeappapi.data.model.response;

import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.RecipeModel;

import java.util.List;

public class ResponseByCategory {
    private int status ;
    private List<RecipeModel> posts;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecipeModel> getPosts() {
        return posts;
    }

    public void setPosts(List<RecipeModel> posts) {
        this.posts = posts;
    }
}
