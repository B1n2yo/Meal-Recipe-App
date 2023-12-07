package use_case.WeeklyDiet;

public class WeeklyDietInputData {

    final private String username;
    final private boolean switchToExerciseView;

    public WeeklyDietInputData(String username, boolean switchToExerciseView) {
        this.username = username;
        this.switchToExerciseView = switchToExerciseView;
    }
    String getUsername() {
        return username;
    }
    boolean getSwitchToExerciseView() { return this.switchToExerciseView; }
}
