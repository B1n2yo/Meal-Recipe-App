package interface_adapter.Signup;

import java.util.ArrayList;

public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String gender = "";
    private float weight = 0;
    private float height = 0;
    private int age = 0;
    private ArrayList<String> dietaryRestrictions = new ArrayList<>();
    private float weeklyBudget = 0;
    private int recommendedDailyCalories = 0;

    public SignupState(SignupState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
        gender = copy.gender;
        weight = copy.weight;
        height = copy.height;
        age = copy.age;
        dietaryRestrictions = copy.dietaryRestrictions;
        weeklyBudget = copy.weeklyBudget;
        recommendedDailyCalories = copy.recommendedDailyCalories;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }
    public String getGender() { return gender; }
    public float getWeight() { return weight; }
    public float getHeight() { return height; }
    public int getAge() { return age; }
    public ArrayList<String> getDietaryRestrictions() { return dietaryRestrictions; }
    public float getWeeklyBudget() { return weeklyBudget; }
    public int getRecommendedDailyCalories() { return recommendedDailyCalories; }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }
    public void setGender(String gender) { this.gender = gender; }
    public void setWeight(float weight) { this.weight = weight; }
    public void setHeight(float height) { this.height = height; }
    public void setAge(int age) { this.age = age; }
    public void setDietaryRestrictions(ArrayList<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions; }
    public void addRestriction(String restriction) {
        this.dietaryRestrictions.add(restriction);
    }
    public void removeRestriction(String restriction) {
        this.dietaryRestrictions.remove(restriction);
    }
    public void setWeeklyBudget(float weeklyBudget) { this.weeklyBudget = weeklyBudget; }
    public void setRecommendedDailyCalories(int recommendedDailyCalories) {
        this.recommendedDailyCalories = recommendedDailyCalories; }


    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
