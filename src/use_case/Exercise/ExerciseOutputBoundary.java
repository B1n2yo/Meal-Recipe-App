package use_case.Exercise;

public interface ExerciseOutputBoundary {
    void prepareSuccessView(ExerciseOutputData user);

    void prepareFailView(String error); 
}
