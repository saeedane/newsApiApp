package com.example.recipeappapi.adapter.databinding;

import android.net.Uri;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.recipeappapi.data.model.RecipeModel;

public class WebViewAdapterBinding {

    @BindingAdapter("webUrl")
    public static void loadUrl(WebView webView, String url) {



        if (url != null) {
            webView.loadDataWithBaseURL("", url, "text/html", "utf-8", null);
        }



    }


}
