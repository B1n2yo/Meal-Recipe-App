//package interface_adapter.Exercise;
//
//import interface_adapter.ViewManagerModel;
//import use_case.Exercise.ExerciseOutputBoundary;
//import use_case.Exercise.ExerciseOutputData;
//
//public class ExercisePresenter implements ExerciseOutputBoundary {
//
//    private final ExerciseViewModel exerciseViewModel;
//
//    private ViewManagerModel viewManagerModel;
//
//    public ExercisePresenter(ExerciseViewModel exerciseViewModel, ViewManagerModel viewManagerModel) {
//        this.exerciseViewModel = exerciseViewModel;
//        this.viewManagerModel = viewManagerModel;
//    }
//
//    @Override
//    public void prepareSuccessView(ExerciseOutputData user) {
//        ExerciseState exerciseState = exerciseViewModel.getState();
//        this.exerciseViewModel.setState(exerciseState);
//        this.exerciseViewModel.firePropertyChanged();
//
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        System.out.println(error);
//    }
//}
