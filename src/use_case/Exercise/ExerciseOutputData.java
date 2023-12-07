package use_case.Exercise;

public class ExerciseOutputData {

    private final String exercisePerformed;
    private final float caloriesBurned;

    private boolean useCaseFailed; // for invalid characters

    public ExerciseOutputData(String exercisePerformed, float caloriesBurned) {
        this.exercisePerformed = exercisePerformed;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExercisePerformed() { return exercisePerformed; }
    public float getCaloriesBurned() {
        return caloriesBurned;
    }
}
