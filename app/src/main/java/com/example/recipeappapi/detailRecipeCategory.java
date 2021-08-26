package com.example.recipeappapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recipeappapi.data.FavoriteDatabase;
import com.example.recipeappapi.data.model.Category;
import com.example.recipeappapi.data.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailRecipeCategory extends AppCompatActivity {
    RecyclerView categoryRecyclerView;
    RecyclerViewRecipe mAdapter;
    ArrayList<Recipe> categoryModelsList;
    RequestQueue rqCat;
    SwipeRefreshLayout swipeCategory;
    Menu menuItem;
    RecyclerView.LayoutManager gridLayout;
    RecyclerView.LayoutManager listLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe_category);


        rqCat = Volley.newRequestQueue(getApplicationContext());
        listLayout = new LinearLayoutManager(this);
        gridLayout = new GridLayoutManager(this,2);

        categoryRecyclerView =  findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(listLayout);
        categoryModelsList = new ArrayList<>();
        String categoryTitle  = getIntent().getExtras().getString("category");
        getSupportActionBar().setTitle(categoryTitle);
        String categoryId = getIntent().getExtras().getString("id");

        sendCategoryRequest("http://192.168.1.9/api/posts?id=" + categoryId);














    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuItem = menu;
        getMenuInflater().inflate(R.menu.menu_item,menu);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                categoryModelsList.clear();
                if (!query.equals("")) {

                    sendCategoryRequest("http://192.168.1.9/api/posts/filter?title=" + query);

                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                categoryModelsList.clear();

                if (!newText.equals("")){
                    sendCategoryRequest("http://192.168.1.9/api/posts/filter?title="  + newText);

                }

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.grid_item:
                item.setVisible(false);
                menuItem.findItem(R.id.list_item).setVisible(true);
                categoryRecyclerView.setLayoutManager(gridLayout);
                return true;


            case R.id.list_item:
                item.setVisible(false);
                menuItem.findItem(R.id.grid_item).setVisible(true);
                categoryRecyclerView.setLayoutManager(listLayout);
                return true;


        }
        return super.onOptionsItemSelected(item);
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

                jsonRequestData(response);




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

    private void jsonRequestData(JSONObject response) {


        JSONArray jsonArray = null;
        try {
            jsonArray = response.getJSONArray("posts");
            for(int i = 0; i < jsonArray.length(); i++){
                Recipe recipe = new Recipe();

                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    recipe.setId(jsonObject.getInt("id"));
                    recipe.setTitle(jsonObject.getString("title"));
                    recipe.setImage(jsonObject.getString("image_path"));
                    recipe.setCategory_name(jsonObject.getString("views"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                categoryModelsList.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mAdapter = new RecyclerViewRecipe(getApplicationContext(), categoryModelsList);
        categoryRecyclerView.setAdapter(mAdapter);
    }


}
