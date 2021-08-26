package com.example.recipeappapi.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipeappapi.R;
import com.example.recipeappapi.RecyclerViewCategory;
import com.example.recipeappapi.data.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    RecyclerView categoryRecyclerView;
    RecyclerViewCategory mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Category> categoryModelsList;
    RequestQueue rqCat;

    public CategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //categoryRecyclerView start
        //Vertical one column
        rqCat = Volley.newRequestQueue(getActivity());
        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.recycleViewCategory);
        categoryRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryModelsList = new ArrayList<>();
        sendCategoryRequest("http://192.168.160.1/api/posts/category");

    }




    //==========================================================================//
    public void sendCategoryRequest(String url){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() == 0)
                {
                    //No result found!
                    Toast.makeText(getActivity(), R.string.txt_no_result, Toast.LENGTH_SHORT).show();
                }
                for(int i = 0; i < response.length(); i++){
                        Category categoryModel = new Category();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        categoryModel.setCategoryId(jsonObject.getString("id"));
                        categoryModel.setCategoryTitle(jsonObject.getString("name"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    categoryModelsList.add(categoryModel);
                }

                mAdapter = new RecyclerViewCategory(getActivity(), categoryModelsList);
                categoryRecyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("BlueDev Volley Error: ", error+"");
                Toast.makeText(getActivity(), R.string.txt_no_result, Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(25000,2,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rqCat.add(jsonArrayRequest);
    }
}
