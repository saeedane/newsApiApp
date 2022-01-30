package com.example.recipeappapi.adapter.databinding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public  class ImageAdapterBinding {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Log.v("image","image url :  " + url);
        if (!url.isEmpty()) {
            Picasso.get().load(url).into(view);

        }else{
            Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(view);

        }
    }
}
