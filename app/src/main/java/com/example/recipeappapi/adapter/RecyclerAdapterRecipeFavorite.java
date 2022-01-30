package com.example.recipeappapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.FavoriteItemBinding;
import com.example.recipeappapi.databinding.ItemCategoryBinding;
import com.example.recipeappapi.databinding.ItemRecipeBinding;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapterRecipeFavorite extends RecyclerView.Adapter<RecyclerAdapterRecipeFavorite.viewFavoriteHolder> {
    private Context context;
    private List<FavoriteModel> favoriteList;
    private FavoriteItemBinding mainRecipe;

    public RecyclerAdapterRecipeFavorite(Context context, List<FavoriteModel> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public viewFavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mainRecipe = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.favorite_item,parent,false);
        return new viewFavoriteHolder(mainRecipe);
    }

    @Override
    public void onBindViewHolder(@NonNull viewFavoriteHolder holder, int position) {

        FavoriteModel favoriteModel = favoriteList.get(position);
        holder.bind(favoriteModel);


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }


    public class viewFavoriteHolder extends RecyclerView.ViewHolder {
        private FavoriteItemBinding binding;
        public viewFavoriteHolder(FavoriteItemBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }
        public void bind(FavoriteModel favoriteModel){
            binding.setFavoriteItem(favoriteModel);

        }

    }
}
