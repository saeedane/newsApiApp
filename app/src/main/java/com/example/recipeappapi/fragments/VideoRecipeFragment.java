package com.example.recipeappapi.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerAdapterCategory;
import com.example.recipeappapi.adapter.RecyclerAdapterRecipeVideo;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.VideoRecipeModel;
import com.example.recipeappapi.databinding.FragmentFavoriteBinding;
import com.example.recipeappapi.databinding.FragmentVideoRecipeBinding;

import java.util.ArrayList;

public class VideoRecipeFragment extends Fragment {
    private FragmentVideoRecipeBinding mainFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainFragment = DataBindingUtil.inflate(inflater, R.layout.fragment_video_recipe,container,false);
        return mainFragment.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<VideoRecipeModel> videoRecipeList = new ArrayList<>();
        videoRecipeList.add(new VideoRecipeModel("video","https://1iyg1fpr5u43muz2368ns59r-wpengine.netdna-ssl.com/wp-content/uploads/2020/01/Jackfruit_Buffalo_Bites_Website-610x330.jpg","100"));
        videoRecipeList.add(new VideoRecipeModel("video","https://1iyg1fpr5u43muz2368ns59r-wpengine.netdna-ssl.com/wp-content/uploads/2020/01/Jackfruit_Buffalo_Bites_Website-610x330.jpg","1000"));
        videoRecipeList.add(new VideoRecipeModel("video","https://1iyg1fpr5u43muz2368ns59r-wpengine.netdna-ssl.com/wp-content/uploads/2020/01/Jackfruit_Buffalo_Bites_Website-610x330.jpg","200"));
        videoRecipeList.add(new VideoRecipeModel("video","https://1iyg1fpr5u43muz2368ns59r-wpengine.netdna-ssl.com/wp-content/uploads/2020/01/Jackfruit_Buffalo_Bites_Website-610x330.jpg","300"));


        RecyclerAdapterRecipeVideo recyclerAdapterRecipeVideo = new RecyclerAdapterRecipeVideo(getContext(),videoRecipeList);
        mainFragment.rvVideo.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mainFragment.rvVideo.setAdapter(recyclerAdapterRecipeVideo);

    }
}
