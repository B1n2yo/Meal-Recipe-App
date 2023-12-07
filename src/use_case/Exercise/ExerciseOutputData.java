package use_case.Exercise;

public class ExerciseOutputData {

    private final String exerciseName;

    private final int duration;
    private final int caloriesBurned;

    private boolean useCaseFailed; // for invalid characters

    public ExerciseOutputData(String exerciseName, int duration, int caloriesBurned) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExercise() {
        return exerciseName;
    }

    public int getDuration() {
        return duration;
    }
    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}
