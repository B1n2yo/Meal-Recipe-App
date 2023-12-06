package app;

import entity.UserProfileFactory;
import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WeeklyDietController;
import interface_adapter.WeeklyDietPresenter;
import use_case.weekly_diet.WeeklyDietDataAccessInterface;
import use_case.weekly_diet.WeeklyDietInputBoundary;
import use_case.weekly_diet.WeeklyDietInteractor;
import use_case.weekly_diet.WeeklyDietOutputBoundary;

public class WeeklyDietControllerFactory {
    private WeeklyDietControllerFactory() {}

    public static WeeklyDietController createWeeklyDietController(LoggedInViewModel loggedInViewModel,
                                                                  ViewManagerModel viewManagerModel,
                                                                  WeeklyDietDataAccessInterface dataAccessObject,
                                                                  UserProfileFactory userProfileFactory) {
        WeeklyDietOutputBoundary weeklyDietPresenter = new WeeklyDietPresenter(viewManagerModel, loggedInViewModel);
        WeeklyDietInputBoundary weeklyDietUseCaseInteractor = new WeeklyDietInteractor(dataAccessObject,
                weeklyDietPresenter, userProfileFactory);
        return new WeeklyDietController(weeklyDietUseCaseInteractor);
    }
}
