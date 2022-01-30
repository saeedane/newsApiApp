package com.example.recipeappapi.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerAdapterCategory;
import com.example.recipeappapi.adapter.RecyclerAdapterSubCategory;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.data.remote.RetrofitApi;
import com.example.recipeappapi.data.remote.RetrofitService;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryFragment extends Fragment {
    private RecipeDataViewModel categoryViewModel;
    private   List<RecipeModel> recipeModelArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  RecyclerAdapterSubCategory recyclerAdapterSubCategory;
    private RecyclerView rv_sub_category;
    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         id = getArguments().getInt("id");

        return inflater.inflate(R.layout.fragment_sub_category,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_sub_category = view.findViewById(R.id.rv_sub_category);
        recipeModelArrayList = new ArrayList<>();
        swipeRefreshLayout = view.findViewById(R.id.swr_sub_category);
        Toast.makeText(view.getContext(),"id" + id,Toast.LENGTH_LONG).show();

        getRecipe();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecipe();
            }
        });










    }


    public void getRecipe() {
        swipeRefreshLayout.setRefreshing(true);

        categoryViewModel  = ViewModelProviders.of(this).get(RecipeDataViewModel.class);
        categoryViewModel.getPostsByCategoryRepository(2).observe(this, new Observer<ResponseByCategory>() {
            @Override
            public void onChanged(ResponseByCategory responseByCategory) {
                recipeModelArrayList.clear();
                if (responseByCategory != null) {
                    for (int i = 0; i < responseByCategory.getPosts().size(); i++) {
                        swipeRefreshLayout.setRefreshing(false);
                        recipeModelArrayList.add( responseByCategory.getPosts().get(i));
                        setRecyclerAdapter();
                    }

                }
            }
        });
    }

    private void setRecyclerAdapter(){
         recyclerAdapterSubCategory = new RecyclerAdapterSubCategory(getActivity(),recipeModelArrayList);
        rv_sub_category.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rv_sub_category.setAdapter(recyclerAdapterSubCategory);

    }
}
