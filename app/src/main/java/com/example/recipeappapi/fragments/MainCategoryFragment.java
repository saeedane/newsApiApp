package com.example.recipeappapi.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.RecyclerAdapterCategory;
import com.example.recipeappapi.adapter.RecyclerAdapterSubCategory;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.response.ResponseByCategory;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.data.remote.RetrofitApi;
import com.example.recipeappapi.data.remote.RetrofitService;
import com.example.recipeappapi.databinding.FragmentMainCategoryBinding;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainCategoryFragment extends Fragment {
    private RecyclerView rv_category;
    private RecipeDataViewModel categoryViewModel;
    private  List<CategoryModel> listCategory = new ArrayList<>();
    private FragmentMainCategoryBinding fragmentMainCategoryBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainCategoryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_category,container,false);
        return fragmentMainCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCategory();

        fragmentMainCategoryBinding.srCategory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCategory();

            }
        });


    }


    public void getCategory() {
        fragmentMainCategoryBinding.srCategory.setRefreshing(true);


        categoryViewModel  = ViewModelProviders.of(this).get(RecipeDataViewModel.class);
        categoryViewModel.getCategoryRepository().observe(getActivity(), new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if (responseModel != null){
                    fragmentMainCategoryBinding.srCategory.setRefreshing(false);
                    listCategory.clear();
                    for (int i = 0 ; i < responseModel.getCategory().size();i++){
                        listCategory.add(responseModel.getCategory().get(i));

                    }

                }

                setRecyclerAdapter();
            }
        });

    }

    private void setRecyclerAdapter(){

        RecyclerAdapterCategory recyclerAdapterCategory = new RecyclerAdapterCategory(getContext(),listCategory);
        fragmentMainCategoryBinding.rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        fragmentMainCategoryBinding.rvCategory.setAdapter(recyclerAdapterCategory);


    }





}
