package com.example.recipeappapi.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerMultiNews;
import com.example.recipeappapi.adapter.SliderRecipeViewPager;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.data.model.SliderModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.TimerTask;

public class HomeActivityFragment extends Fragment {
    private SliderRecipeViewPager sliderRecipeViewPager;
    private ArrayList<RecipeModel> recipeModels = new ArrayList<>();

    private RecyclerMultiNews recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        RecyclerView recyclerView = view.findViewById(R.id.recyclerMultiNews);
        recipeModels.add(new RecipeModel("عنوان خبر 1 ","https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/courgette_pakoras_83738_16x9.jpg"));
        recipeModels.add(new RecipeModel("عنوان خبر 2 ","https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/courgette_pakoras_83738_16x9.jpg"));
        recipeModels.add(new RecipeModel("عنوان خبر 3 ","https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/courgette_pakoras_83738_16x9.jpg"));
        recipeModels.add(new RecipeModel("عنوان خبر 4 ","https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/courgette_pakoras_83738_16x9.jpg"));
        recipeModels.add(new RecipeModel("عنوان خبر 5 ","https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/courgette_pakoras_83738_16x9.jpg"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerMultiNews(getContext(),recipeModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);



    }





}
