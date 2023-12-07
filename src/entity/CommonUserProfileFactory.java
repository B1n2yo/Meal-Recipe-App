package entity;

import java.util.ArrayList;

public class CommonUserProfileFactory implements UserProfileFactory {

    @Override
    public UserProfile create(String username, String password, String gender, float weight, float height, int age,
                              ArrayList<String> dietaryRestrictions, float recommendedDailyCalories,
                              ArrayList<String> recipes) {
        return new UserProfile(username, password, gender, weight, height, age, dietaryRestrictions,
                recommendedDailyCalories, recipes);
    }
}
