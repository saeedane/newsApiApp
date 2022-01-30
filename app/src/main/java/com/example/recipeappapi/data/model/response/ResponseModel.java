package com.example.recipeappapi.data.model.response;

import com.example.recipeappapi.data.model.CategoryModel;

import java.util.List;

public class ResponseModel {
    private int status ;
    private List<CategoryModel> category;

    public int getStatus() {
        return status;
    }


    public List<CategoryModel> getCategory() {
        return category;
    }


}
