package data_access;

public class ExerciseData {
    private final String exerciseName;
    private final int duration;
    private final int caloriesBurned;

    public ExerciseData(String exerciseName, int duration, int caloriesBurned) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}
