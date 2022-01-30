package com.example.recipeappapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.R;
import com.example.recipeappapi.data.model.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class SliderRecipeViewPager extends PagerAdapter {

    private Context context;
    private ArrayList<SliderModel> theSlideItemsModelClassList;

    public SliderRecipeViewPager(Context mContext, ArrayList<SliderModel> theSlideItemsModelClassList) {
        this.context = mContext;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        SliderModel sliderModel = theSlideItemsModelClassList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.custom_items_layout_slider,null);
        ImageView imageSlider = sliderLayout.findViewById(R.id.imageSlider);
        TextView titleSlider = sliderLayout.findViewById(R.id.titleSlider);

        Glide.with(context).load(theSlideItemsModelClassList.get(position).getImageSlider()).into(imageSlider);
        titleSlider.setText(theSlideItemsModelClassList.get(position).getTitleSlider());

        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return theSlideItemsModelClassList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
