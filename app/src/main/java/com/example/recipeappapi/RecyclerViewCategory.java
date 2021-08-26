package com.example.recipeappapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.data.model.Category;
import com.example.recipeappapi.databinding.ItemCategoryBinding;

import java.util.ArrayList;

public class RecyclerViewCategory extends RecyclerView.Adapter<RecyclerViewCategory.ViewHolderRecipe> {

    ArrayList<Category> categories ;

    private Context mContext;


    public RecyclerViewCategory(Context context,ArrayList<Category> categories ) {
        this.mContext = context;
        this.categories = categories;

    }

    @NonNull
    @Override
    public ViewHolderRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category, parent, false);
        return new ViewHolderRecipe(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecipe holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
        Glide.with(mContext).load(category.getPhoto()).into(holder.binding.photo); // replace with your fav image loading lib

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String categoryId = category.getCategoryId();
                String categoryTitle = category.getCategoryTitle();


                Intent intent = new Intent(mContext.getApplicationContext(),detailRecipeCategory.class);
                intent.putExtra("id",categoryId);
                intent.putExtra("category",categoryTitle);
                v.getContext().startActivity(intent);


            }
        });









    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolderRecipe extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public ViewHolderRecipe(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Category category) {
            binding.setCategory(category);
        }
    }





}
