package com.example.recipeappapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.ItemFirstNewsBinding;
import com.example.recipeappapi.databinding.ItemListNewsBinding;
import com.example.recipeappapi.databinding.ItemRecipeByCategoryBinding;
import com.example.recipeappapi.databinding.ItemStorieBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMultiNews extends RecyclerView.Adapter<RecyclerMultiNews.ViewHolderNews> {

    private Context context;
    private ArrayList<RecipeModel> newsList;
    private ItemFirstNewsBinding itemGridNews;
    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;
    private ItemListNewsBinding itemListNews;

    public RecyclerMultiNews(Context context, ArrayList<RecipeModel> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == LAYOUT_ONE){
            itemGridNews = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_first_news,parent,false);
            return new RecyclerMultiNews.ViewHolderNews(itemGridNews);

        }else{
            itemListNews = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_news,parent,false);
            return new RecyclerMultiNews.ViewHolderNews(itemListNews);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNews holder, int position) {
        if(holder.getItemViewType() == LAYOUT_ONE){
            RecipeModel recipeModel = newsList.get(position);
            holder.bindGrid(recipeModel);
        }else{
            RecipeModel recipeModel = newsList.get(position);
            holder.bindList(recipeModel);
        }

    }

    @Override
    public int getItemCount() {
        if (newsList.size() > 6){
            return 6;
        }
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;

    }



    public class ViewHolderNews extends RecyclerView.ViewHolder {
        ItemFirstNewsBinding itemFirstNewsBinding;
        ItemListNewsBinding itemListNews;
        public ViewHolderNews(@NonNull ItemFirstNewsBinding itemFirstNewsView) {
            super(itemFirstNewsView.getRoot());
            this.itemFirstNewsBinding = itemFirstNewsView;
        }

        public ViewHolderNews(ItemListNewsBinding itemListNews) {
            super(itemListNews.getRoot());
            this.itemListNews = itemListNews;

        }

        public void bindGrid(RecipeModel recipeModel){
            itemFirstNewsBinding.setNewsGridItem(recipeModel);

        }
        public void bindList(RecipeModel recipeModel){
            itemListNews.setNewsItemList(recipeModel);

        }
    }


}
