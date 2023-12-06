package interface_adapter.Logged_in;

import entity.MealInfo;

import java.util.ArrayList;

public class LoggedInState {
    private String username = "";
    private ArrayList<MealInfo> weeklyDiet;

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMealPlan(ArrayList<MealInfo> weeklyDiet) {
        this.weeklyDiet = weeklyDiet;
    }

    public ArrayList<MealInfo> getMealPlan() {
        return weeklyDiet;
    }
}
