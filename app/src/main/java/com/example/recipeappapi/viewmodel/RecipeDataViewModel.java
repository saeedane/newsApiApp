package com.example.recipeappapi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recipeappapi.data.RecipeDataRepository;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseLastNews;
import com.example.recipeappapi.data.model.response.ResponseModel;

import java.util.List;

public class RecipeDataViewModel extends AndroidViewModel{
    private  RecipeDataRepository  repository;
    private LiveData<ResponseModel> mutableLiveData;
    private LiveData<ResponseByCategory> categoryMutableLiveData;
    private LiveData<List<FavoriteModel>> favoriteModelLiveData;
    private LiveData<ResponseLastNews> getLastNewsLiveData;


    public RecipeDataViewModel(@NonNull Application application) {
        super(application);
        repository = RecipeDataRepository.getInstance(application);
        mutableLiveData = repository.getAllCategory();
        favoriteModelLiveData = repository.getAllFavoriteRecipe();
        getLastNewsLiveData = repository.getAllLastNews();

    }



    public LiveData<ResponseLastNews> getLastNewsRepository(){

        return getLastNewsLiveData;

    }

    public LiveData<ResponseModel> getCategoryRepository(){

        return mutableLiveData;

    }

    public LiveData<ResponseByCategory> getPostsByCategoryRepository(int id){
        categoryMutableLiveData = repository.getRecipeByCategory(id);

        return categoryMutableLiveData;

    }


    /**
     * init favorite
     * @return
     */

    public void addFavorite(FavoriteModel favoriteModel) {

          repository.addFavorite(favoriteModel);

    }
    public int isFavorite(int id) {

        return repository.isFavorite(id);

    }
    public LiveData<List<FavoriteModel>> getAllFavoriteRecipe() {

        return favoriteModelLiveData;

    }

    public void deleteFavorite(FavoriteModel favoriteModel) {

         repository.deleteFavorite(favoriteModel);

    }



    @Override
    protected void onCleared() {
        // Cancel all ongoing requests
        repository.cancelDataRequests();
    }


}
