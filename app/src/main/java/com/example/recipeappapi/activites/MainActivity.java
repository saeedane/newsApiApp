package com.example.recipeappapi.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recipeappapi.R;
import com.example.recipeappapi.adapter.pager.showFragmentViewPager;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.data.model.response.ResponseModel;
import com.example.recipeappapi.databinding.ActivityMainBinding;
import com.example.recipeappapi.fragments.FavoriteFragment;
import com.example.recipeappapi.fragments.HomeActivityFragment;
import com.example.recipeappapi.fragments.SubCategoryFragment;
import com.example.recipeappapi.fragments.VideoRecipeFragment;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding mainView;
    private static final String ONESIGNAL_APP_ID = "1f4f8db6-edda-43db-99fe-42aba850ccd0";
    private RecipeDataViewModel categoryViewModel;
    private List<CategoryModel> listCategory = new ArrayList<>();
    private  TabLayout tabLayout;
    ArrayList<String> array_cat_name;
    String[]  str_news_cat_name;
    showFragmentViewPager adapter;
                   ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainView = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainView.getRoot());


        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        array_cat_name = new ArrayList<>();
        str_news_cat_name = new String[listCategory.size()];

        tabLayout=findViewById(R.id.tabLayout);
          viewPager=findViewById(R.id.viewPager);
          Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("الجزائر  أخبار");
        toolbar.setSubtitle("كل 24  ساعة ");

        setSupportActionBar(toolbar);



          adapter = new showFragmentViewPager(getSupportFragmentManager());


        categoryViewModel  = ViewModelProviders.of(this).get(RecipeDataViewModel.class);
        categoryViewModel.getCategoryRepository().observe(MainActivity.this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                array_cat_name.clear();

                if (responseModel != null){

                    for (int i = 0 ; i < responseModel.getCategory().size();i++){

                        CategoryModel categoryModel = new CategoryModel();
                        categoryModel.setCategoryTitle(responseModel.getCategory().get(i).getCategoryTitle());
                        listCategory.add(categoryModel);





                            array_cat_name.add(listCategory.get(i).getCategoryTitle());
                            str_news_cat_name = array_cat_name.toArray(str_news_cat_name);
                        tabLayout.addTab(tabLayout.newTab().setText( str_news_cat_name[i]));

                        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",responseModel.getCategory().get(i).getCategoryId());
                        subCategoryFragment.setArguments(bundle);
                        adapter.addFragment(subCategoryFragment,str_news_cat_name[i]);

                            Log.v("category","category name : " + str_news_cat_name[i]);


                    }


                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                 tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                viewPager.setAdapter(adapter);
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                     viewPager.setCurrentItem(tab.getPosition() );

               


                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

                }

            }
        });





































    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
