package com.example.recipeappapi.adapter.pager;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipeappapi.adapter.RecyclerMultiNews;
import com.example.recipeappapi.data.model.CategoryModel;
import com.example.recipeappapi.fragments.HomeActivityFragment;
import com.example.recipeappapi.fragments.MainCategoryFragment;
import com.example.recipeappapi.fragments.SubCategoryFragment;
import com.example.recipeappapi.fragments.VideoRecipeFragment;

import java.util.ArrayList;
import java.util.List;

public class showFragmentViewPager extends FragmentPagerAdapter {

    List<Fragment> fragments = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();

    public showFragmentViewPager(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0 ){
            return new HomeActivityFragment();
        }

        return fragments.get(position);


    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return fragments.size() ;
    }

    public  void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        fragmentTitle.add(title);

    }

}