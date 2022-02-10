package com.example.recipeappapi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerAdapterCategory;
import com.example.recipeappapi.adapter.RecyclerAdapterRecipeRating;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.RecipeModel;

import java.util.ArrayList;

public class MainFragmentRecipe extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_recipe,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv_rating = view.findViewById(R.id.rv_recipe_rating);
        ArrayList<RecipeModel> recipeModels = new ArrayList<>();

        RecyclerAdapterRecipeRating adapterRecipeRating = new RecyclerAdapterRecipeRating(getContext(),recipeModels);
        rv_rating.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_rating.setAdapter(adapterRecipeRating);




    }
}
