package com.example.recipeappapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.data.model.ItemRecipe;
import com.example.recipeappapi.data.model.Recipe;
import com.example.recipeappapi.databinding.ItemListRecipeBinding;
import com.example.recipeappapi.databinding.ItemRecipeFavoriteBinding;

import java.util.ArrayList;

public class RecyclerViewRecipeFavorite extends RecyclerView.Adapter<RecyclerViewRecipeFavorite.ViewHolderRecipe> {

    ArrayList<ItemRecipe> categories ;

    private Context mContext;


    public RecyclerViewRecipeFavorite(Context context, ArrayList<ItemRecipe> categories ) {
        this.mContext = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public ViewHolderRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeFavoriteBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recipe_favorite, parent, false);
        return new RecyclerViewRecipeFavorite.ViewHolderRecipe(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecipe holder, int position) {
        ItemRecipe recipe = categories.get(position);
        holder.bind(recipe);
        Glide.with(mContext).load(recipe.getImage()).into(holder.binding.photo); // replace with your fav image loading lib












    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolderRecipe extends RecyclerView.ViewHolder {
        ItemRecipeFavoriteBinding binding;

        public ViewHolderRecipe(@NonNull ItemRecipeFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemRecipe recipe) {
            binding.setFavorite(recipe);
        }
    }



}
