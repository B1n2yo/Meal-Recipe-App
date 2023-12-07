package use_case.WeeklyDiet;

public interface WeeklyDietOutputBoundary {
    void prepareSuccessViewRecipe(WeeklyDietOutputData diet);
    void prepareSuccessViewSwitch(String username);
}
