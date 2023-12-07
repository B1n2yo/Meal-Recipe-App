package use_case.Exercise;

public class ExerciseInputData {

    final private String username;
    final private String exercisePerformed;

    public ExerciseInputData(String username, String exercisePerformed) {
        this.username = username;
        this.exercisePerformed = exercisePerformed;
    }

    public String getUsername() {
        return username;
    }
    public String getExercisePerformed() {
        return exercisePerformed;
    }
}
