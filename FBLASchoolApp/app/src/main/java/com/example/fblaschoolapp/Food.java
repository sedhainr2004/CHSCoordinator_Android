package com.example.fblaschoolapp;

public class Food {

    private String foodTitle;
    private String calories;
    private String imageURL;
    public Food()
    {

    }
    public Food(String foodTitle, String calories, String imageURL) {
        this.foodTitle = foodTitle;
        this.calories = calories;
        this.imageURL = imageURL;
    }
    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
