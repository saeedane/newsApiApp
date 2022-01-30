package com.example.recipeappapi.adapter.pager;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.recipeappapi.adapter.RecyclerMultiNews;
import com.example.recipeappapi.fragments.HomeActivityFragment;
import com.example.recipeappapi.fragments.MainCategoryFragment;
import com.example.recipeappapi.fragments.SubCategoryFragment;
import com.example.recipeappapi.fragments.VideoRecipeFragment;

public class showFragmentViewPager extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;

    public showFragmentViewPager(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeActivityFragment homeFragment = new HomeActivityFragment();
                return homeFragment;

            default:
                SubCategoryFragment subCategoryFragment = new SubCategoryFragment();

                Bundle args = new Bundle();
                args.putInt("id", 2);
                subCategoryFragment.setArguments(args);
                return subCategoryFragment ;
        }


    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}