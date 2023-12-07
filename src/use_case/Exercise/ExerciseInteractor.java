package use_case.Exercise;
import api.NutritionixAPICall;
import entity.UserProfile;

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
        String gender = user.getGender();
        float weight = user.getWeight();
        float height = user.getHeight();
        int age = user.getAge();

        String query =
                "{\n" +
                        "\"query\" : \"" + exercisePerformed + "\",\n" +
                        "\"gender\" : \"" + gender + "\",\n" +
                        "\"weight_kg\" : \"" + weight + "\",\n" +
                        "\"height_cm\" : \"" + height + "\",\n" +
                        "\"age\" : \"" + age + "\"\n" +
                        "}";

        NutritionixAPICall apicall = new NutritionixAPICall();
        float caloriesBurned = apicall.caloriesBurned(query);
        dataAccessObject.updateCalories(username, caloriesBurned);

        ExerciseOutputData exerciseOutputData = new ExerciseOutputData(exercisePerformed, caloriesBurned);
        exercisePresenter.prepareSuccessView(exerciseOutputData);

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
