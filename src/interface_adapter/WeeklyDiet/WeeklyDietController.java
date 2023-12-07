package interface_adapter.WeeklyDiet;

import use_case.WeeklyDiet.WeeklyDietInputBoundary;
import use_case.WeeklyDiet.WeeklyDietInputData;

public class WeeklyDietController {

    final WeeklyDietInputBoundary weeklyDietUseCaseInteractor;
    public WeeklyDietController(WeeklyDietInputBoundary weeklyDietUseCaseInteractor) {
        this.weeklyDietUseCaseInteractor = weeklyDietUseCaseInteractor;
    }

    public void execute(String username, boolean switchToExerciseView) {
        WeeklyDietInputData weeklyDietInputData = new WeeklyDietInputData(username, switchToExerciseView);

        weeklyDietUseCaseInteractor.execute(weeklyDietInputData);
    }
}
