package com.example.recipeappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipeappapi.data.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView categoryRecyclerView;
    RecyclerViewCategory mAdapter;
    ArrayList<Category> categoryModelsList;
    RequestQueue rqCat;
    SwipeRefreshLayout swipeCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeCategory = findViewById(R.id.swipeRefCategory);

        rqCat = Volley.newRequestQueue(getApplicationContext());
        categoryRecyclerView =  findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        categoryModelsList = new ArrayList<>();


        Button button = findViewById(R.id.btn_favorite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),favoriteActivity.class));
            }
        });
        sendCategoryRequest("http://192.168.1.9/api/posts/category");


        swipeCategory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (categoryModelsList.size() > 0) {

                    setRecyclerAdapterData();

                }

            }
        });


    }





    //==========================================================================//
    public void sendCategoryRequest(String url){

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,url , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() == 0)
                {
                    //No result found!
                    Toast.makeText(getApplicationContext(), R.string.txt_no_result, Toast.LENGTH_SHORT).show();
                }


                Log.v(TAG,"response : " + response.toString());


                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("category");
                    for(int i = 0; i < jsonArray.length(); i++){
                        Category categoryModel = new Category();

                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            categoryModel.setCategoryId(jsonObject.getString("id"));
                            categoryModel.setCategoryTitle(jsonObject.getString("name"));
                            categoryModel.setPhoto(jsonObject.getString("photo"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        categoryModelsList.add(categoryModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setRecyclerAdapterData();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("BlueDev Volley Error: ", error+"");
                Toast.makeText(getApplicationContext(), R.string.txt_no_result, Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(25000,2,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rqCat.add(jsonArrayRequest);
    }


    private void setRecyclerAdapterData(){

        mAdapter = new RecyclerViewCategory(getApplicationContext(), categoryModelsList);
        categoryRecyclerView.setAdapter(mAdapter);
        swipeCategory.setRefreshing(false);

    }






}
