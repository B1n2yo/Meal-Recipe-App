package app;

import interface_adapter.Exercise.ExerciseController;
import interface_adapter.Exercise.ExercisePresenter;
import interface_adapter.Exercise.ExerciseViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Exercise.ExerciseDataAccessInterface;
import use_case.Exercise.ExerciseInputBoundary;
import use_case.Exercise.ExerciseInteractor;
import use_case.Exercise.ExerciseOutputBoundary;
import view.ExerciseView;

import javax.swing.*;
import java.io.IOException;

public class ExerciseUseCaseFactory {
    /** Prevent instantiation. */
    private ExerciseUseCaseFactory() {}

    public static ExerciseView create(
            ViewManagerModel viewManagerModel,
            ExerciseViewModel exerciseViewModel,
            ExerciseDataAccessInterface userDataAccessObject) {

        try {
            ExerciseController exerciseController = createExerciseUseCase(viewManagerModel,exerciseViewModel, userDataAccessObject);
            return new ExerciseView(exerciseViewModel, exerciseController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ExerciseController createExerciseUseCase(
            ViewManagerModel viewManagerModel,
            ExerciseViewModel exerciseViewModel,
            ExerciseDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ExerciseOutputBoundary exerciseOutputBoundary = new ExercisePresenter(exerciseViewModel, viewManagerModel);

        ExerciseInputBoundary exerciseInteractor = new ExerciseInteractor(userDataAccessObject, exerciseOutputBoundary);

        return new ExerciseController(exerciseInteractor);
    }
}
