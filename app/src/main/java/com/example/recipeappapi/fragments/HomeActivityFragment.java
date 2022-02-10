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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerMultiNews;
import com.example.recipeappapi.adapter.SliderRecipeViewPager;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.data.model.SliderModel;
import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseLastNews;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.TimerTask;

public class HomeActivityFragment extends Fragment {
    private SliderRecipeViewPager sliderRecipeViewPager;
    private ArrayList<RecipeModel> recipeModels = new ArrayList<>();

    private RecyclerMultiNews recyclerAdapter;
    private RecipeDataViewModel newsResponse;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




         recyclerView = view.findViewById(R.id.recyclerMultiNews);

        newsResponse  = ViewModelProviders.of(this).get(RecipeDataViewModel.class);
        newsResponse.getLastNewsRepository().observe(this, new Observer<ResponseLastNews>() {
            @Override
            public void onChanged(ResponseLastNews responseLastNews) {
                if (responseLastNews != null){
                    for (int i = 0; i < responseLastNews.getLastNewsModels().size(); i++) {

                        recipeModels.add(responseLastNews.getLastNewsModels().get(i));
                        setupAdapterRecyclerNews();

                    }

                }
            }
        });


    }

    private void setupAdapterRecyclerNews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerMultiNews(getContext(),recipeModels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);


    }


}
