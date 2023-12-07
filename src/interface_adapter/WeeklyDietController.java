package interface_adapter;

import use_case.weekly_diet.WeeklyDietInputBoundary;
import use_case.weekly_diet.WeeklyDietInputData;

public class WeeklyDietController {

    final WeeklyDietInputBoundary weeklyDietUseCaseInteractor;
    public WeeklyDietController(WeeklyDietInputBoundary weeklyDietUseCaseInteractor) {
        this.weeklyDietUseCaseInteractor = weeklyDietUseCaseInteractor;
    }

    public void execute(String username) {
        WeeklyDietInputData weeklyDietInputData = new WeeklyDietInputData(username);

        weeklyDietUseCaseInteractor.execute(weeklyDietInputData);
    }
}
