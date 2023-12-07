package app;

import data_access.DataAccessObject;
import entity.CommonUserProfileFactory;
import entity.UserProfileFactory;
import interface_adapter.Exercise.ExerciseViewModel;
import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Logout.LogoutController;
import interface_adapter.Logout.LogoutViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;

//import view.LoggedInView;
import interface_adapter.WeeklyDiet.WeeklyDietController;
import view.*;
//import view.LoggedInView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Meal-Recipe-App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LogoutViewModel logoutViewModel = new LogoutViewModel();
        ExerciseViewModel exerciseViewModel = new ExerciseViewModel();

        DataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new DataAccessObject("./userProfiles.csv", new CommonUserProfileFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, signupViewModel,
        userDataAccessObject);
        views.add(loginView, loginView.viewName);

        ExerciseView exerciseView = ExerciseUseCaseFactory.create(viewManagerModel, exerciseViewModel,
                loggedInViewModel, userDataAccessObject);
        views.add(exerciseView, exerciseView.viewName);

        UserProfileFactory userProfileFactory = new CommonUserProfileFactory();

        WeeklyDietController weeklyDietController = WeeklyDietControllerFactory.createWeeklyDietController(
                loggedInViewModel, exerciseViewModel, viewManagerModel, userDataAccessObject, userProfileFactory);

        LogoutController logoutController = LogoutControllerFactory.createLogoutController(viewManagerModel,
                logoutViewModel);

        LoggedInView loggedInView = new LoggedInView(weeklyDietController, loggedInViewModel, logoutController);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
