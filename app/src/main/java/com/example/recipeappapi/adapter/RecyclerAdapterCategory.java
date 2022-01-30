package com.example.recipeappapi.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.Constant;
import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.databinding.ItemCategoryBinding;
import com.example.recipeappapi.databinding.ItemStorieBinding;
import com.example.recipeappapi.fragments.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.viewCategoryHolder> {
    private Context context;
    private List<CategoryModel> categoryList;
    private ItemStorieBinding mainCategory;

    public RecyclerAdapterCategory(Context context, List<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public viewCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mainCategory = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_storie,parent,false);
        return new viewCategoryHolder(mainCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull viewCategoryHolder holder, int position) {

        CategoryModel categoryModel = categoryList.get(position);
        holder.bind(categoryModel);



    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class viewCategoryHolder extends RecyclerView.ViewHolder {
        private ItemStorieBinding binding;
        public viewCategoryHolder(ItemStorieBinding mBinding) {
            super(mBinding.getRoot());
            this.binding = mBinding;
        }
        public void bind(CategoryModel categoryModel){
            binding.setCategoryItem(categoryModel);

        }

    }
}
