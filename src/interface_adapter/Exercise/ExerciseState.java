package interface_adapter.Exercise;

public class ExerciseState {

    private String username = "";
    private String exerciseName = "";
    private String successMessage = null;

    public ExerciseState(ExerciseState copy) {
        this.username = copy.username;
        this.exerciseName = copy.exerciseName;
        this.successMessage = copy.successMessage;
    }
    public ExerciseState() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSuccessMessage() { return successMessage; }

    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }

    public String getExerciseName() { return exerciseName; }

    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }
}
