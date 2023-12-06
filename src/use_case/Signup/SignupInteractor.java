package use_case.Signup;
import entity.UserProfile;
import entity.UserProfileFactory;

import java.util.ArrayList;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserProfileFactory userProfileFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserProfileFactory userProfileFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userProfileFactory = userProfileFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String repeatPassword = signupInputData.getRepeatPassword();
        String gender = signupInputData.getGender();
        float weight = signupInputData.getWeight();
        float height = signupInputData.getHeight();
        int age = signupInputData.getAge();
        ArrayList<String> dietaryRestrictions = signupInputData.getDietaryRestrictions();
        float weeklyBudget = signupInputData.getWeeklyBudget();
        int recommendedDailyCalories = signupInputData.getRecommendedDailyCalories();

        if (userDataAccessObject.existsByName(username)) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(repeatPassword)) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else if (!(gender.equals("Male") || signupInputData.getGender().equals("Female"))) {
            userPresenter.prepareFailView("Input either \"Male\" or \"Female\".");
        } else if ((0.0 > weight) || (weight > 300.0)) {
            userPresenter.prepareFailView("Enter a weight between 0.0kg and 300.0kg.");
        } else if ((0.0 > height) || (height > 250.0)) {
            userPresenter.prepareFailView("Enter a height between 0.0cm and 250.0cm.");
        } else if ((0 > age) || (age > 120)) {
            userPresenter.prepareFailView("Enter an age between 0 years and 120 years.");
        } else {
            // check that the passwords are equal.
            assert !(userDataAccessObject.existsByName(username));
            assert (password.equals(repeatPassword));
            assert (gender.equals("Male") || gender.equals("Female"));
            assert ((0.0 > weight) || (weight > 300.0));
            assert  ((0.0 > height) || (height > 250.0));
            UserProfile user = userProfileFactory.create(username, password, gender, weight, height, age,
                    dietaryRestrictions, weeklyBudget, recommendedDailyCalories);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}