package com.example.recipeappapi.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("categories")
    Call<ResponseModel> getAllCategory();
    @GET("posts-by-category")
    Call<ResponseByCategory> getPostsByCategory(@Query("cid") int id);


}
