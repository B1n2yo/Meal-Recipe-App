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
        int recommendedDailyCalories = signupInputData.getRecommendedDailyCalories();
        ArrayList<String> recipes = signupInputData.getRecipes();

        if (userDataAccessObject.existsByName(username)) {
            userPresenter.prepareFailViewUsername("User already exists.");
        } else if (username.isEmpty()) {
            userPresenter.prepareFailViewUsername("Please input a username.");
        } else if (!signupInputData.getPassword().equals(repeatPassword)) {
            userPresenter.prepareFailViewPassword("Passwords don't match.");
        } else if (password.isEmpty()) {
            userPresenter.prepareFailViewPassword("Please input a password.");
        } else if (repeatPassword.isEmpty()) {
            userPresenter.prepareFailViewPassword("Please repeat the password.");
        } else if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            userPresenter.prepareFailViewGender("Input either \"Male\" or \"Female\".");
        } else if ((0.0 > weight) || (weight > 300.0)) {
            userPresenter.prepareFailViewWeight("Enter a weight between 0.0kg and 300.0kg.");
        } else if ((0.0 > height) || (height > 250.0)) {
            userPresenter.prepareFailViewHeight("Enter a height between 0.0cm and 250.0cm.");
        } else if ((0 > age) || (age > 120)) {
            userPresenter.prepareFailViewAge("Enter an age between 0 years and 120 years.");
        } else {
            // check that the passwords are equal.
            assert !(userDataAccessObject.existsByName(username));
            assert (password.equals(repeatPassword));
            assert (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
            assert ((0.0 > weight) || (weight > 300.0));
            assert  ((0.0 > height) || (height > 250.0));
            UserProfile user = userProfileFactory.create(username, password, gender, weight, height, age,
                    dietaryRestrictions, recommendedDailyCalories, recipes);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}