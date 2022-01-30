package com.example.recipeappapi.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerAdapterCategory;
import com.example.recipeappapi.adapter.RecyclerAdapterRecipeFavorite;
import com.example.recipeappapi.data.locale.DatabaseRecipeFavorite;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.databinding.FragmentFavoriteBinding;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding mainFragment;
    private RecipeDataViewModel recipeDataViewModel;
    private List<FavoriteModel>favoriteModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainFragment = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container,false);
        return mainFragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        getRecipeFavorite();







    }



    public void getRecipeFavorite() {
        recipeDataViewModel = ViewModelProviders.of(this).get(RecipeDataViewModel.class);
        favoriteModels = new ArrayList<>();
        recipeDataViewModel.getAllFavoriteRecipe().observe(getActivity(), new Observer<List<FavoriteModel>>() {
            @Override
            public void onChanged(List<FavoriteModel> favoriteModelList) {
                if (favoriteModelList.size()> 0 ) {
                    mainFragment.rvFavorite.setVisibility(View.VISIBLE);
                    favoriteModels.clear();
                    favoriteModels.addAll(favoriteModelList);
                }else {
                    mainFragment.linearLayoutNoFavorite.setVisibility(View.VISIBLE);
                    mainFragment.rvFavorite.setVisibility(View.GONE);

                }
            }
        });

        setRecyclerAdapter();
    }


    private void setRecyclerAdapter(){


            RecyclerAdapterRecipeFavorite recipeFavorite = new RecyclerAdapterRecipeFavorite(getActivity(),favoriteModels);
            mainFragment.rvFavorite.setAdapter(recipeFavorite);




    }

}
