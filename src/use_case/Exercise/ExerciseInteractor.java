package use_case.Exercise;
import api.NutritionixAPICall;
import entity.UserProfile;

import java.util.Objects;

public class ExerciseInteractor implements ExerciseInputBoundary {

    final ExerciseDataAccessInterface dataAccessObject;

    final ExerciseOutputBoundary exercisePresenter;

    public ExerciseInteractor(ExerciseDataAccessInterface dataAccessObject, ExerciseOutputBoundary exercisePresenter) {
        this.dataAccessObject = dataAccessObject;
        this.exercisePresenter = exercisePresenter;
    }

    @Override
    public void execute(ExerciseInputData exerciseInputData) {
        String username = exerciseInputData.getUsername();
        String exercisePerformed = exerciseInputData.getExercisePerformed();
        UserProfile user = dataAccessObject.get(username);

        NutritionixAPICall apicall = new NutritionixAPICall();
        if (!Objects.equals(exercisePerformed, "")) {
            float caloriesBurned = apicall.caloriesBurned(exercisePerformed, user);
            dataAccessObject.updateCalories(username, caloriesBurned);
            ExerciseOutputData exerciseOutputData = new ExerciseOutputData(exercisePerformed, caloriesBurned);
            exercisePresenter.prepareSuccessView(exerciseOutputData);
        }
        else {
            exercisePresenter.prepareFailView("No exercises were recognized in your request.");
        }


//        String username = exerciseInputData.getUsername();
//        String exercisePerformed = exerciseInputData.getExercisePerformed();
//        ExerciseData exerciseData = dataAccessObject.call(username, exercisePerformed);
//        if (exerciseData == null) {
//            exercisePresenter.prepareFailView("No exercises were recognized in your request.");
//        }
//        else {
//            String exerciseName = exerciseData.getExerciseName();
//            int duration = exerciseData.getDuration();
//            int caloriesBurned = exerciseData.getCaloriesBurned();
//            ExerciseOutputData exerciseOutputData = new ExerciseOutputData(exerciseName, duration, caloriesBurned);
//            exercisePresenter.prepareSuccessView(exerciseOutputData);
//        }
    }
}
