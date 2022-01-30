package com.example.recipeappapi.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.recipeappapi.R;
import com.example.recipeappapi.data.locale.Dao.FavoriteRecipeDao;
import com.example.recipeappapi.data.locale.DatabaseRecipeFavorite;
import com.example.recipeappapi.data.model.FavoriteModel;
import com.example.recipeappapi.data.model.RecipeModel;
import com.example.recipeappapi.databinding.DetailActivityBinding;
import com.example.recipeappapi.fragments.DetailsVideoRecipe;
import com.example.recipeappapi.fragments.InstructionRecipeFragment;
import com.example.recipeappapi.fragments.MainCategoryFragment;
import com.example.recipeappapi.fragments.SubCategoryFragment;
import com.example.recipeappapi.viewmodel.RecipeDataViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class DetailsRecipeActivity extends AppCompatActivity {
    private DetailActivityBinding detailActivityBinding;
    private   RecipeModel recipeModel;
    private RecipeDataViewModel recipeDataViewModel;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            detailActivityBinding = DetailActivityBinding.inflate(getLayoutInflater());
            setContentView(detailActivityBinding.getRoot());

            // init view model
            recipeDataViewModel = ViewModelProviders.of(this).get(RecipeDataViewModel.class);


            if (getIntent().getExtras() != null){
                recipeModel = getIntent().getParcelableExtra("data");


                if (recipeModel != null){
                    detailActivityBinding.setDetailsRecipe(recipeModel);
                    detailActivityBinding.crDetails.setVisibility(View.VISIBLE);
                    detailActivityBinding.progressBar.setVisibility(View.GONE);
                    if (recipeDataViewModel.isFavorite(recipeModel.getContent_id()) == 1){
                        detailActivityBinding.imgFav.setImageResource(R.drawable.ic_favorite_gray);

                    }else{
                        detailActivityBinding.imgFav.setImageResource(R.drawable.ic_favorite_border_white);

                    }



                }
            }
            // favorite button
            detailActivityBinding.imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavoriteModel favoriteModel = new FavoriteModel();
                    favoriteModel.setIdFavorite(recipeModel.getContent_id());
                    favoriteModel.setTitleFavorite(recipeModel.getContent_title());
                    favoriteModel.setImageFavorite(recipeModel.getContent_image());
                    if (recipeDataViewModel.isFavorite(recipeModel.getContent_id()) != 1){
                        detailActivityBinding.imgFav.setImageResource(R.drawable.ic_favorite_gray);
                        recipeDataViewModel.addFavorite(favoriteModel);
                    }else{
                        detailActivityBinding.imgFav.setImageResource(R.drawable.ic_favorite_border_white);
                        recipeDataViewModel.deleteFavorite(favoriteModel);
                    }
                }
            });




            ViewPagerFragmentRecipe viewPagerFragmentRecipe = new ViewPagerFragmentRecipe(this);
            detailActivityBinding.vpInstruction.setAdapter(viewPagerFragmentRecipe);
            // attaching tab mediator
            new TabLayoutMediator(detailActivityBinding.tabIndicator, detailActivityBinding.vpInstruction, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    if(position == 0){
                        tab.setText(getString(R.string.video));

                    }else{
                        tab.setText(getString(R.string.instructions));

                    }

                }


            }).attach();






    }


    class ViewPagerFragmentRecipe extends FragmentStateAdapter{



        public ViewPagerFragmentRecipe(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new DetailsVideoRecipe().newInstance(recipeModel.getVideo_url());

                case 1:
                    return new InstructionRecipeFragment().newInstant(recipeModel.getContent_body());
            }


            return null;
        }

        @Override
        public int getItemCount() {
            return 2;
        }


    }


}
