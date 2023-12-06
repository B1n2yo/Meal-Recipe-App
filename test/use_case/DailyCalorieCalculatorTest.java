//package use_case;
//
//import org.junit.Test;
//
//import use_case.DailyCalorieCalculator.DailyCalorieCalculatorInteractor;
//import use_case.DailyCalorieCalculator.DailyCalorieCalculatorOutputBoundary;
//import use_case.DailyCalorieCalculator.DailyCalorieCalculatorInputData;
//import use_case.DailyCalorieCalculator.DailyCalorieCalculatorOutputData;
//public class DailyCalorieCalculatorTest {
//
//        @Test
//        public void testCalculateCaloriesMale() {
//            DailyCalorieCalculatorOutputBoundary dailyCalorieCalculatorPresenter = new DailyCalorieCalculatorOutputBoundary() {
//                @Override
//                public void prepareSuccessView(DailyCalorieCalculatorOutputData dailyCalorieCalculatorOutputData) {
//                    assert dailyCalorieCalculatorOutputData.getRecCalories() == 2335.0;
//                }
//
//                @Override
//                public void prepareFailView(String error) {
//
//                }
//            };
//            DailyCalorieCalculatorInputData InData = new DailyCalorieCalculatorInputData("male", 20, 70, 150);
//            DailyCalorieCalculatorInteractor Interactor = new DailyCalorieCalculatorInteractor(dailyCalorieCalculatorPresenter);
//            Interactor.execute(InData);
//
//        }
//        @Test
//        public void testCalculateCaloriesFemale() {
//            DailyCalorieCalculatorOutputBoundary dailyCalorieCalculatorPresenter = new DailyCalorieCalculatorOutputBoundary() {
//                @Override
//                public void prepareSuccessView(DailyCalorieCalculatorOutputData dailyCalorieCalculatorOutputData) {
//                    assert dailyCalorieCalculatorOutputData.getRecCalories() == 2127.0;
//                }
//
//                @Override
//                public void prepareFailView(String error) {
//
//                }
//            };
//            DailyCalorieCalculatorInputData InData = new DailyCalorieCalculatorInputData("female", 20, 70, 150);
//            DailyCalorieCalculatorInteractor Interactor = new DailyCalorieCalculatorInteractor(dailyCalorieCalculatorPresenter);
//            Interactor.execute(InData);
//        }
//}