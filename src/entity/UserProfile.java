package entity;

import java.util.ArrayList;

public class UserProfile {
    private String username;
    private String password;
    private String gender;
    private float weight;
    private float height;
    private int age;
    private ArrayList<String> dietaryRestrictions;
    private float weeklyBudget;
    private float recommendedDailyCalories;
    private ArrayList<String> recipes;

    public UserProfile(String username, String password, String gender, float weight, float height, int age,
                       ArrayList<String> dietaryRestrictions, float weeklyBudget, float recommendedDailyCalories,
                       ArrayList<String> recipes) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.dietaryRestrictions = dietaryRestrictions;
        this.weeklyBudget = weeklyBudget;
        this.recommendedDailyCalories = recommendedDailyCalories;
        this.recipes = recipes;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getGender() {
        return this.gender;
    }
    public float getWeight() {
        return this.weight;
    }
    public float getHeight() {
        return this.height;
    }
    public int getAge() {
        return this.age;
    }
    public ArrayList<String> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }
    public float getWeeklyBudget() {
        return this.weeklyBudget;
    }

    public float getRecommendedDailyCalories() {
        return this.recommendedDailyCalories;
    }
    public void setRecommendedDailyCalories(float recommendedDailyCalories) {
        this.recommendedDailyCalories = recommendedDailyCalories; }

    public ArrayList<String> getRecipes() {
        return this.recipes;
    }
    public void addRecipe(String recipe) {
        this.recipes.add(recipe);
    }
}
