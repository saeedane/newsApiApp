package com.example.recipeappapi.adapter.databinding;

import android.view.View;
import android.webkit.WebView;

import androidx.databinding.BindingAdapter;

public class WebViewVideoYoutubeUrl {
    @BindingAdapter("webUrlVideo")
    public static void loadVideoUrl(WebView webView, String url) {

        String html   = getHtmlVideo(url);
        webView.getSettings().setJavaScriptEnabled(true);

        if (url != null) {
            webView.loadDataWithBaseURL("", html, "text/html", "utf-8", null);
        }else{
            webView.setVisibility(View.GONE);
        }



    }

    private  static String getHtmlVideo(String url){

        String html = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"http://www.youtube.com/embed/"
                + url
                + "?fs=0\" frameborder=\"0\">\n"
                + "</iframe>\n";

        return html;
    }
}
