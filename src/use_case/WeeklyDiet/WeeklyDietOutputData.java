package use_case.WeeklyDiet;

import entity.MealInfo;
import entity.UserProfile;

import java.util.ArrayList;

public class WeeklyDietOutputData {
    private final ArrayList<MealInfo> weeklyDiet;
    private String creationTime;
    private UserProfile userProfile;
    private boolean useCaseFailed;

    public WeeklyDietOutputData(ArrayList<MealInfo> weeklyDiet, String creationTime,
                                UserProfile userProfile,boolean useCaseFailed) {
        this.weeklyDiet = weeklyDiet;
        this.creationTime = creationTime;
        this.userProfile = userProfile;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<MealInfo> getWeeklyDiet() {
        return weeklyDiet;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public UserProfile getUserProfile() { return this.userProfile; }
}
