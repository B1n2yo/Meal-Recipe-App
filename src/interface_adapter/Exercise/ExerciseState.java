package interface_adapter.Exercise;

public class ExerciseState {

    private String username = "";
    private String exerciseName = "";

    private int duration = 0;

    private int caloriesBurned = 0;

    public ExerciseState(ExerciseState copy) {
        exerciseName = copy.exerciseName;
    }

    public ExerciseState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExerciseName() {
        return exerciseName;
    }


    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
