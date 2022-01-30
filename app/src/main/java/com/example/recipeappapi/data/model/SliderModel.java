package com.example.recipeappapi.data.model;

public class SliderModel {
    private String imageSlider;
    private String titleSlider;

    public SliderModel(String imageSlider, String titleSlider) {
        this.imageSlider = imageSlider;
        this.titleSlider = titleSlider;
    }

    public String getImageSlider() {
        return imageSlider;
    }

    public void setImageSlider(String imageSlider) {
        this.imageSlider = imageSlider;
    }

    public String getTitleSlider() {
        return titleSlider;
    }

    public void setTitleSlider(String titleSlider) {
        this.titleSlider = titleSlider;
    }
}
