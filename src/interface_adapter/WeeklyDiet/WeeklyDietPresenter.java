package interface_adapter.WeeklyDiet;

import interface_adapter.Exercise.ExerciseState;
import interface_adapter.Exercise.ExerciseViewModel;
import interface_adapter.Logged_in.LoggedInState;
import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.WeeklyDiet.WeeklyDietOutputBoundary;
import use_case.WeeklyDiet.WeeklyDietOutputData;

public class WeeklyDietPresenter implements WeeklyDietOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ExerciseViewModel exerciseViewModel;
    private ViewManagerModel viewManagerModel;

    public WeeklyDietPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel, ExerciseViewModel exerciseViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.exerciseViewModel = exerciseViewModel;
    }

    @Override
    public void prepareSuccessViewRecipe(WeeklyDietOutputData response) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setMealPlan(response.getWeeklyDiet());
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
    }
    public void prepareSuccessViewSwitch(String username) {
        ExerciseState exerciseState = exerciseViewModel.getState();
        exerciseState.setUsername(username);
        exerciseViewModel.setState(exerciseState);
        exerciseViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(exerciseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
