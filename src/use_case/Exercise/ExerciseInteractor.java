//package use_case.Exercise;
//
//import data_access.ExerciseData;
//
//public class ExerciseInteractor implements ExerciseInputBoundary {
//
//    final ExerciseDataAccessInterface dataAccessObject;
//
//    final ExerciseOutputBoundary exercisePresenter;
//
//    public ExerciseInteractor(ExerciseDataAccessInterface dataAccessObject, ExerciseOutputBoundary exercisePresenter) {
//        this.dataAccessObject = dataAccessObject;
//        this.exercisePresenter = exercisePresenter;
//    }
//
//    @Override
//    public void execute(ExerciseInputData exerciseInputData) {
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
//        // old code before switch to using API
////        switch (exerciseType) {
////            case "Walking_slow":
////                MET = 2;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Walking_moderate":
////                MET = 2.6F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Walking_fast":
////                MET = 3.6F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Running_slow":
////                MET = 7.6F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Running_moderate":
////                MET = 9.9F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Running_fast":
////                MET = 12.2F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Cycling_slow":
////                MET = 7.6F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Cycling_moderate":
////                MET = 9.9F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Cycling_fast":
////                MET = 11.4F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Swimming_light/moderate":
////                MET = 5.8F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            case "Swimming_fast/vigorous":
////                MET = 9.9F;
////                caloriesBurned = (int) (MET * weight * duration * (3.5 / 200));
////                break;
////            default:
//                // prepare fail view
////        }
////        ExerciseOutputData exerciseOutputData = new ExerciseOutputData(caloriesBurned);
//    }
//}
