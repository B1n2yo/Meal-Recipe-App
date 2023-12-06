package interface_adapter;

import interface_adapter.Logged_in.LoggedInState;
import interface_adapter.Logged_in.LoggedInViewModel;
import use_case.weekly_diet.WeeklyDietOutputBoundary;
import use_case.weekly_diet.WeeklyDietOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeeklyDietPresenter implements WeeklyDietOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public WeeklyDietPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(WeeklyDietOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setMealPlan(response.getWeeklyDiet());
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
    }
}
