package com.example.recipeappapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.ItemCategoryBinding;
import com.example.recipeappapi.databinding.ItemRecipeBinding;

import java.util.ArrayList;


public class RecyclerAdapterRecipeRating extends RecyclerView.Adapter<RecyclerAdapterRecipeRating.viewCategoryHolder> {
    private Context context;
    private ArrayList<RecipeModel> recipeList;
    private ItemRecipeBinding mainRecipe;

    public RecyclerAdapterRecipeRating(Context context, ArrayList<RecipeModel> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public viewCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mainRecipe = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_recipe,parent,false);
        return new viewCategoryHolder(mainRecipe);
    }

    @Override
    public void onBindViewHolder(@NonNull viewCategoryHolder holder, int position) {

        RecipeModel recipeModel = recipeList.get(position);
        holder.bind(recipeModel);


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public class viewCategoryHolder extends RecyclerView.ViewHolder {
        private ItemRecipeBinding binding;
        public viewCategoryHolder(ItemRecipeBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }
        public void bind(RecipeModel recipeModel){
            binding.setRecipeItem(recipeModel);

        }

    }
}
