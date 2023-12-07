package use_case.WeeklyDiet;

import entity.MealInfo;
import entity.UserProfile;

import java.util.ArrayList;

public class WeeklyDietOutputData {
    private final ArrayList<MealInfo> weeklyDiet;
    private UserProfile userProfile;

    public WeeklyDietOutputData(ArrayList<MealInfo> weeklyDiet, UserProfile userProfile) {
        this.weeklyDiet = weeklyDiet;
        this.userProfile = userProfile;
    }

    public ArrayList<MealInfo> getWeeklyDiet() {
        return weeklyDiet;
    }

    public UserProfile getUserProfile() { return this.userProfile; }
}
