package app;

import interface_adapter.Logout.LogoutController;
import interface_adapter.Logout.LogoutPresenter;
import interface_adapter.Logout.LogoutViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Logout.LogoutInputBoundary;
import use_case.Logout.LogoutInteractor;
import use_case.Logout.LogoutOutputBoundary;
import use_case.weekly_diet.WeeklyDietDataAccessInterface;

import java.io.IOException;

public class LogoutControllerFactory {
    private LogoutControllerFactory() {}

    public static LogoutController createLogoutController(ViewManagerModel viewManagerModel,
                                                          LogoutViewModel logoutViewModel) throws IOException {

        LogoutOutputBoundary logoutPresenter = new LogoutPresenter(logoutViewModel, viewManagerModel);
        LogoutInputBoundary logoutUseCaseInteractor = new LogoutInteractor(logoutPresenter);

        return new LogoutController(logoutUseCaseInteractor);
    }
}
