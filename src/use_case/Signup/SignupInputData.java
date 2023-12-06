package use_case.Signup;

import java.util.ArrayList;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String gender;
    final private float weight;
    final private float height;
    final private int age;
    final private ArrayList<String> dietaryRestrictions;
    final private float weeklyBudget;
    final private int recommendedDailyCalories;

    public SignupInputData(String username, String password, String repeatPassword, String gender, float weight,
                           float height, int age, ArrayList<String> dietaryRestrictions, float weeklyBudget,
                           int recommendedDailyCalories) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.dietaryRestrictions = dietaryRestrictions;
        this.weeklyBudget = weeklyBudget;
        this.recommendedDailyCalories = recommendedDailyCalories;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
    public String getGender() { return this.gender; }
    public float getWeight() { return weight; }
    public float getHeight() { return height; }
    public int getAge() { return age; }
    public ArrayList<String> getDietaryRestrictions() { return dietaryRestrictions; }
    public float getWeeklyBudget() { return weeklyBudget; }
    public int getRecommendedDailyCalories() { return recommendedDailyCalories; }
}