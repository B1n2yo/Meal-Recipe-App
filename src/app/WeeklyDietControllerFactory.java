package app;

import entity.UserProfileFactory;
import interface_adapter.Exercise.ExerciseViewModel;
import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.WeeklyDiet.WeeklyDietController;
import interface_adapter.WeeklyDiet.WeeklyDietPresenter;
import use_case.WeeklyDiet.WeeklyDietDataAccessInterface;
import use_case.WeeklyDiet.WeeklyDietInputBoundary;
import use_case.WeeklyDiet.WeeklyDietInteractor;
import use_case.WeeklyDiet.WeeklyDietOutputBoundary;

public class WeeklyDietControllerFactory {
    private WeeklyDietControllerFactory() {}

    public static WeeklyDietController createWeeklyDietController(LoggedInViewModel loggedInViewModel,
                                                                  ExerciseViewModel exerciseViewModel,
                                                                  ViewManagerModel viewManagerModel,
                                                                  WeeklyDietDataAccessInterface dataAccessObject,
                                                                  UserProfileFactory userProfileFactory) {
        WeeklyDietOutputBoundary weeklyDietPresenter = new WeeklyDietPresenter(viewManagerModel, loggedInViewModel,
                exerciseViewModel);
        WeeklyDietInputBoundary weeklyDietUseCaseInteractor = new WeeklyDietInteractor(dataAccessObject,
                weeklyDietPresenter, userProfileFactory);
        return new WeeklyDietController(weeklyDietUseCaseInteractor);
    }
}
