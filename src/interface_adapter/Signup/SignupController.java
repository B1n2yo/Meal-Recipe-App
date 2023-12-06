package interface_adapter.Signup;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

import java.util.ArrayList;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2, String gender, float weight, float height,
                        int age, ArrayList<String> dietaryRestrictions,  float weeklyBudget,
                        int recommendedDailyCalories) {
        SignupInputData signupInputData = new SignupInputData(username, password1, password2, gender, weight, height,
                age, dietaryRestrictions, weeklyBudget, recommendedDailyCalories);
        this.userSignupUseCaseInteractor.execute(signupInputData);
    }
}
