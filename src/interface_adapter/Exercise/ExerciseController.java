package interface_adapter.Exercise;

import use_case.Exercise.ExerciseInputBoundary;
import use_case.Exercise.ExerciseInputData;

public class ExerciseController {
    final ExerciseInputBoundary userExerciseUseCaseInteractor;

    public ExerciseController(ExerciseInputBoundary userExerciseUseCaseInteractor) {
        this.userExerciseUseCaseInteractor = userExerciseUseCaseInteractor;
    }

    public void execute(String username, String exercisePerformed) {
        ExerciseInputData exerciseInputData = new ExerciseInputData(username, exercisePerformed);
        userExerciseUseCaseInteractor.execute(exerciseInputData);
    }
}
