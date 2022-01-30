package com.example.recipeappapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.VideoRecipeModel;
import com.example.recipeappapi.databinding.FavoriteItemBinding;
import com.example.recipeappapi.databinding.ItemVideoRecipeBinding;

import java.util.ArrayList;


public class RecyclerAdapterRecipeVideo extends RecyclerView.Adapter<RecyclerAdapterRecipeVideo.viewHolderVideo> {
    private Context context;
    private ArrayList<VideoRecipeModel> videoRecipeModelList;
    private ItemVideoRecipeBinding mainRecipe;

    public RecyclerAdapterRecipeVideo(Context context, ArrayList<VideoRecipeModel> videoRecipeModelList) {
        this.context = context;
        this.videoRecipeModelList = videoRecipeModelList;
    }

    @NonNull
    @Override
    public viewHolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mainRecipe = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_video_recipe,parent,false);
        return new viewHolderVideo(mainRecipe);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderVideo holder, int position) {

        VideoRecipeModel favoriteModel = videoRecipeModelList.get(position);
        holder.bind(favoriteModel);


    }

    @Override
    public int getItemCount() {
        return videoRecipeModelList.size();
    }


    public class viewHolderVideo extends RecyclerView.ViewHolder {
        private ItemVideoRecipeBinding binding;
        public viewHolderVideo(ItemVideoRecipeBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }
        public void bind(VideoRecipeModel videoRecipeModel){
            binding.setVideoItem(videoRecipeModel);

        }

    }
}
