package use_case.WeeklyDiet;

import entity.UserProfile;

public interface WeeklyDietDataAccessInterface {

    UserProfile getUserProfile(String username);

    void saveRecipe(String recipeName, UserProfile userProfile);

    boolean recipeSaved(String recipeName, UserProfile userProfile);
}
