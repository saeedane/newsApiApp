package com.example.recipeappapi.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.recipeappapi.R;
import com.example.recipeappapi.activites.DetailsRecipeActivity;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.FragmentVideoDetailsLayoutBinding;

public class DetailsVideoRecipe extends Fragment {
    private FragmentVideoDetailsLayoutBinding fragmentVideoDetailsLayoutBinding;
    private static final String ARG_URL = "video_url";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        fragmentVideoDetailsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_details_layout,container,false);
        return fragmentVideoDetailsLayoutBinding.getRoot();
    }


    public static DetailsVideoRecipe newInstance(String url) {
        DetailsVideoRecipe fragment = new DetailsVideoRecipe();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String url = getArguments().getString(ARG_URL);

        fragmentVideoDetailsLayoutBinding.setVideoUrl(url);



        }









}
