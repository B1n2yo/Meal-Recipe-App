package entity;

import java.util.ArrayList;

public class MealInfo implements Meal {
    private String name;
    private String description;
    private float calories;
    private float price;
//    private float carbohydrates;
//    private float fat;
//    private float protein;
//    private float vitamins;
//    private float sodium;
//    private float cholesterol;
//    private String[] ingredients;

    public MealInfo(String name, String description, float calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.price = price;
//        this.carbohydrates = carbohydrates;
//        this.fat = fat;
//        this.protein = protein;
//        this.vitamins = vitamins;
//        this.sodium = sodium;
//        this.cholesterol = cholesterol;
//        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

//    @Override
//    public String[] getIngredients() {
//        return ingredients;
//    }
//
//    @Override
//    public float getPrice() {
//        return price;
//    }

    @Override
    public float getCalories() {
        return calories;
    }
}
