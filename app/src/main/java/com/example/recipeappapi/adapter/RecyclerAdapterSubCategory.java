package com.example.recipeappapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.activites.DetailsRecipeActivity;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.DetailActivityBinding;
import com.example.recipeappapi.databinding.ItemRecipeByCategoryBegBinding;
import com.example.recipeappapi.databinding.ItemRecipeByCategoryBinding;

import java.util.List;

public class RecyclerAdapterSubCategory extends RecyclerView.Adapter<RecyclerAdapterSubCategory.viewHolderRecipe> {
    private Context context;
    private List<RecipeModel> recipeModelArrayList;
    private ItemRecipeByCategoryBinding mainRecipeByCategory;
    private ItemRecipeByCategoryBegBinding mainRecipeByCategoryBeg;
    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;
    public RecyclerAdapterSubCategory(Context context, List<RecipeModel> recipeModelArrayList) {
        this.context = context;
        this.recipeModelArrayList = recipeModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapterSubCategory.viewHolderRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == LAYOUT_ONE){

            mainRecipeByCategoryBeg = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recipe_by_category_beg,parent,false);
            return new RecyclerAdapterSubCategory.viewHolderRecipe(mainRecipeByCategoryBeg);

        }else{
            mainRecipeByCategory = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recipe_by_category,parent,false);
            return new RecyclerAdapterSubCategory.viewHolderRecipe(mainRecipeByCategory);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterSubCategory.viewHolderRecipe holder, int position) {

        RecipeModel recipeModel = recipeModelArrayList.get(position);

        if(holder.getItemViewType() == LAYOUT_ONE){
            holder.bindBeg(recipeModel);

        }else{
            holder.bind(recipeModel);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsRecipeActivity.class);
                intent.putExtra("data",recipeModel);
                v.getContext().startActivity(intent);



            }
        });



    }

    @Override
    public int getItemCount() {
        return recipeModelArrayList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;

    }

    public class viewHolderRecipe extends RecyclerView.ViewHolder {
        private ItemRecipeByCategoryBinding binding;
        private ItemRecipeByCategoryBegBinding bindingBeg;

        public viewHolderRecipe(ItemRecipeByCategoryBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }

        public viewHolderRecipe(ItemRecipeByCategoryBegBinding mBinding) {
            super(mBinding.getRoot());
            this.bindingBeg = mBinding;
        }


        public void bind(RecipeModel recipeModel){
            binding.setNewsItemList(recipeModel);

        }

        public void bindBeg(RecipeModel recipeModel){
            bindingBeg.setNewsBegItem(recipeModel);

        }

    }




}
