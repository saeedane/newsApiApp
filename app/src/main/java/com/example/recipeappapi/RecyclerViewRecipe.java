package com.example.recipeappapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.data.FavoriteDatabase;
import com.example.recipeappapi.data.model.ItemRecipe;
import com.example.recipeappapi.databinding.ItemListRecipeBinding;
import com.example.recipeappapi.data.model.Recipe;

import java.util.ArrayList;

public class RecyclerViewRecipe extends RecyclerView.Adapter<RecyclerViewRecipe.ViewHolderRecipe> {

    ArrayList<Recipe> categories ;

    private Context mContext;


    public RecyclerViewRecipe(Context context,ArrayList<Recipe> categories ) {
        this.mContext = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public ViewHolderRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListRecipeBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_recipe, parent, false);
        return new RecyclerViewRecipe.ViewHolderRecipe(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecipe holder, int position) {
        Recipe recipe = categories.get(position);
        holder.bind(recipe);
        Glide.with(mContext).load(recipe.getImage()).into(holder.binding.image); // replace with your fav image loading lib


        /*
        if (favoriteActivity.favoriteDatabase.favoriteDao().isFavorite(recipe.getId()) == 1){
            holder.binding.imageButton.setImageResource(R.drawable.ic_favorite_red);

        }
        else{
            holder.binding.imageButton.setImageResource(R.drawable.ic_favorite_gray);
        }

         */


        holder.binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemRecipe favoriteList = new ItemRecipe();
                int id = recipe.getId();
                String title = recipe.getTitle();
                String content = recipe.getContent();
                String image = recipe.getImage();
                String category_name = recipe.getCategory_name();
                favoriteList.setId(id);
                favoriteList.setTitle(title);
                favoriteList.setImage(image);
                favoriteList.setContent(content);
                favoriteList.setCategory_name(category_name);



                if (favoriteActivity.favoriteDatabase.favoriteDao().isFavorite(recipe.getId()) != 1){
                    holder.binding.imageButton.setImageResource(R.drawable.ic_favorite_red);
                    favoriteActivity.favoriteDatabase.favoriteDao().addData(favoriteList);



                }else{
                    holder.binding.imageButton.setImageResource(R.drawable.ic_favorite_gray);
                    favoriteActivity.favoriteDatabase.favoriteDao().delete(favoriteList);


                }

            }
        });











    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolderRecipe extends RecyclerView.ViewHolder {
        ItemListRecipeBinding binding;

        public ViewHolderRecipe(@NonNull ItemListRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Recipe recipe) {
            binding.setRecipe(recipe);
        }
    }



}
