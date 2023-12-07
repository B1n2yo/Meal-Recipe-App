package use_case.weekly_diet;

import entity.MealInfo;
import entity.UserProfile;

public interface WeeklyDietDataAccessInterface {

    UserProfile getUserProfile(String username);

    void saveRecipe(MealInfo recipe, UserProfile userProfile);

    boolean recipeSaved(MealInfo recipe, UserProfile userProfile);
}
