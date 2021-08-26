package com.example.recipeappapi.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="favorite")
public class ItemRecipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private String image;
    private String category_name ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
