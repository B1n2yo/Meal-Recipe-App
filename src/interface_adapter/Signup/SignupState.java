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
    private String genderError = null;
    private float weight = 0;
    private String weightError = null;
    private float height = 0;
    private String heightError = null;
    private int age = 0;
    private String ageError = null;
    private ArrayList<String> dietaryRestrictions = new ArrayList<>();
    private int recommendedDailyCalories = 0;
    private ArrayList<String> recipes = new ArrayList<>();

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
        recommendedDailyCalories = copy.recommendedDailyCalories;
        recipes = copy.recipes;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {
    }

    public String getUsername() {
        return username;
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
    public String getGenderError() { return genderError; }
    public float getWeight() { return weight; }
    public String getWeightError() { return weightError; }
    public float getHeight() { return height; }
    public String getHeightError() { return heightError; }
    public int getAge() { return age; }
    public String getAgeError() { return ageError; }
    public ArrayList<String> getDietaryRestrictions() { return dietaryRestrictions; }
    public int getRecommendedDailyCalories() { return recommendedDailyCalories; }
    public ArrayList<String> getRecipes() { return recipes; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) { this.usernameError = usernameError; }

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
    public void setGenderError(String genderError) {
        this.genderError = genderError;
    }
    public void setWeight(float weight) { this.weight = weight; }
    public void setWeightError(String weightError) {
        this.weightError = weightError;
    }

    public void setHeight(float height) { this.height = height; }
    public void setHeightError(String heightError) {
        this.heightError = heightError;
    }

    public void setAge(int age) { this.age = age; }
    public void setAgeError(String ageError) {
        this.ageError = ageError;
    }

    public void addRestriction(String restriction) {
        this.dietaryRestrictions.add(restriction);
    }
    public void removeRestriction(String restriction) {
        this.dietaryRestrictions.remove(restriction);
    }
    public void setRecipes(ArrayList<String> recipes) { this.recipes = recipes; }

    public void resetViewInputs() {
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
        this.repeatPassword = "";
        this.repeatPasswordError = null;
        this.gender = "";
        this.weight = 0;
        this.height = 0;
        this.age = 0;
        this.dietaryRestrictions = new ArrayList<>();
        this.recommendedDailyCalories = 0;
        this.recipes = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
