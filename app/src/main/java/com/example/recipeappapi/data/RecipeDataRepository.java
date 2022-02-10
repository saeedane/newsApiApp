package com.example.recipeappapi.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeappapi.data.locale.DatabaseRecipeFavorite;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseLastNews;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.data.remote.RetrofitApi;
import com.example.recipeappapi.data.remote.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeDataRepository {


    /**
     * Object used for the purpose of synchronize lock
     */
    private static final Object LOCK = new Object();

    /**
     * Instance of this class for Singleton
     */
    private static RecipeDataRepository sInstance;
    private RetrofitService retrofitService;


    private Call<ResponseModel> categoryModelCall;
    private  Call<ResponseLastNews> lastNewsResponse;
    private Call<ResponseByCategory> responseByCategoryCall;
    private static final String TAG = RecipeDataRepository.class.getSimpleName();
    private static DatabaseRecipeFavorite databaseRecipeFavorite;


    /**
     * Method used to get an instance of WeatherDataRepository class
     *
     * @param context Context to use for some initializations
     * @return an instance of WeatherDataRepository class
     */
    public static RecipeDataRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null)
                    sInstance = new RecipeDataRepository(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    /**
     * @param context Context to use for some initializations
     */
    @TargetApi(Build.VERSION_CODES.N)
    private RecipeDataRepository(Context context) {
        // Create a new WeatherInfo call using Retrofit API interface
        categoryModelCall = RetrofitService.getClient().create(RetrofitApi.class).getAllCategory();
        lastNewsResponse = RetrofitService.getClient().create(RetrofitApi.class).getLastNews();
        databaseRecipeFavorite = DatabaseRecipeFavorite.getInstance(context);



    }


    public LiveData<ResponseModel> getAllCategory() {

        MutableLiveData<ResponseModel> responseModelLiveData = new MutableLiveData<>();

        categoryModelCall.clone().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                Log.v("category","response category :" + response.body());

                if (response != null) {

                    if (response.body().getStatus() == 200) {
                        responseModelLiveData.setValue(response.body());


                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.v(TAG, "error response : " + t.getMessage());
            }

        });


        return responseModelLiveData;

    }

    public LiveData<ResponseLastNews> getAllLastNews() {

        MutableLiveData<ResponseLastNews> responseModelLiveData = new MutableLiveData<>();
        lastNewsResponse.clone().enqueue(new Callback<ResponseLastNews>() {
            @Override
            public void onResponse(Call<ResponseLastNews> call, Response<ResponseLastNews> response) {


                if (response != null) {

                    if (response.body().getStatus() == 200) {
                        responseModelLiveData.setValue(response.body());


                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseLastNews> call, Throwable t) {
                Log.v(TAG, "error response : " + t.getMessage());
            }

        });


        return responseModelLiveData;

    }




    public LiveData<ResponseByCategory> getRecipeByCategory(int id) {
        responseByCategoryCall = RetrofitService.getClient().create(RetrofitApi.class).getPostsByCategory(id);

        MutableLiveData<ResponseByCategory> responseModelLiveData = new MutableLiveData<>();

        responseByCategoryCall.clone().enqueue(new Callback<ResponseByCategory>() {
            @Override
            public void onResponse(Call<ResponseByCategory> call, Response<ResponseByCategory> response) {

                if (response != null) {

                    if (response.body().getStatus() == 200) {
                        responseModelLiveData.postValue(response.body());


                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseByCategory> call, Throwable t) {
                responseModelLiveData.postValue(null);
                Log.v(TAG, "error response : " + t.getMessage());
            }

        });


        return responseModelLiveData;

    }

    /**
     * init favorite
     * @return
     */

    public void addFavorite(FavoriteModel favoriteModel) {

         databaseRecipeFavorite.favoriteDao().addData(favoriteModel);

    }
    public int isFavorite(int id) {

        return databaseRecipeFavorite.favoriteDao().isFavorite(id);

    }
    public LiveData<List<FavoriteModel>> getAllFavoriteRecipe() {

        return  databaseRecipeFavorite.favoriteDao().getFavoriteData();

    }

    public void deleteFavorite(FavoriteModel favoriteModel) {

          databaseRecipeFavorite.favoriteDao().delete(favoriteModel);

    }


        /**
         * Cancel all data requests
         */
    public void cancelDataRequests() {
        if (categoryModelCall != null) {
            categoryModelCall.cancel();
        }

    }
}
