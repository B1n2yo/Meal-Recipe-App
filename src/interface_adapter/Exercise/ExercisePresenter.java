package interface_adapter.Exercise;

import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Exercise.ExerciseOutputBoundary;
import use_case.Exercise.ExerciseOutputData;
import view.LoggedInView;

public class ExercisePresenter implements ExerciseOutputBoundary {

    private final ExerciseViewModel exerciseViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private ViewManagerModel viewManagerModel;

    public ExercisePresenter(ExerciseViewModel exerciseViewModel, LoggedInViewModel loggedInViewModel,
                             ViewManagerModel viewManagerModel) {
        this.exerciseViewModel = exerciseViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ExerciseOutputData response) {
        String caloriesBurned = String.valueOf(response.getCaloriesBurned());
        String exercisePerformed = response.getExercisePerformed();

        ExerciseState exerciseState = exerciseViewModel.getState();
        exerciseState.setSuccessMessage(exercisePerformed + " burned " + caloriesBurned + " calories!");
        this.exerciseViewModel.setState(exerciseState);
        this.exerciseViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
