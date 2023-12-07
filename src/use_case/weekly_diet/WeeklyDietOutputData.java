package use_case.weekly_diet;

import entity.MealInfo;

import java.util.ArrayList;

public class WeeklyDietOutputData {
    private final ArrayList<MealInfo> weeklyDiet;

    public WeeklyDietOutputData(ArrayList<MealInfo> weeklyDiet) {
        this.weeklyDiet = weeklyDiet;
    }

    public ArrayList<MealInfo> getWeeklyDiet() {
        return weeklyDiet;
    }
}
