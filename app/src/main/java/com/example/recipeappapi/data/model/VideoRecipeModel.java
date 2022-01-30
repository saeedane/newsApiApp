package com.example.recipeappapi.data.model;

public class VideoRecipeModel {
    private String titleVideo;
    private String urlVideo;
    private String imageVideo;
    private String viewVideo;
    private String dateVideo;

    public VideoRecipeModel(String titleVideo, String imageVideo, String viewVideo) {
        this.titleVideo = titleVideo;
        this.imageVideo = imageVideo;
        this.viewVideo = viewVideo;
    }

    public String getTitleVideo() {
        return titleVideo;
    }

    public void setTitleVideo(String titleVideo) {
        this.titleVideo = titleVideo;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getImageVideo() {
        return imageVideo;
    }

    public void setImageVideo(String imageVideo) {
        this.imageVideo = imageVideo;
    }

    public String getViewVideo() {
        return viewVideo;
    }

    public void setViewVideo(String viewVideo) {
        this.viewVideo = viewVideo;
    }

    public String getDateVideo() {
        return dateVideo;
    }

    public void setDateVideo(String dateVideo) {
        this.dateVideo = dateVideo;
    }
}
