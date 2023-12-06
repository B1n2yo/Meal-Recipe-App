package entity;

import java.util.ArrayList;

public interface UserProfileFactory {
    UserProfile create(String username, String password, String gender, float weight, float height, int age,
                       ArrayList<String> dietaryRestrictions, float weeklyBudget, float recommendedDailyCalories);
}
