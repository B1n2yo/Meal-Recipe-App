package use_case;

import data_access.DataAccessObject;
import entity.CommonUserProfileFactory;
import entity.UserProfile;
import entity.UserProfileFactory;
import org.junit.Test;
import use_case.Login.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginTest {

    @Test
    public void testLogin() {
        UserProfileFactory userProfileFactory = new CommonUserProfileFactory();

        DataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new DataAccessObject("./userProfiles.csv", new CommonUserProfileFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> dietaryRestriction = new ArrayList<>();
        ArrayList<String> recipes = new ArrayList<>();

        UserProfile user = userProfileFactory.create("Fred", "fredtheman", "male", 111, 111, 11, dietaryRestriction, 0, recipes);
        userDataAccessObject.save(user);

        LoginInputData inputData = new LoginInputData("Fred", "fredtheman", false);
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessViewForLogin(LoginOutputData user) {
                assertEquals(user.getUsername(), "Fred");
            }

            @Override
            public void prepareSuccessViewForSwitch() {
                fail("unexpected method call");
            }

            @Override
            public void prepareFailViewUsername(String error) {
                fail("unexpected method call");
            }

            @Override
            public void prepareFailViewPassword(String error) {
                fail("unexpected method call");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testWrongUsername() {
        UserProfileFactory userProfileFactory = new CommonUserProfileFactory();

        DataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new DataAccessObject("./userProfiles.csv", new CommonUserProfileFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> dietaryRestriction = new ArrayList<>();
        ArrayList<String> recipes = new ArrayList<>();

        UserProfile user = userProfileFactory.create("Fred", "fredtheman", "male", 111, 111, 11, dietaryRestriction, 0, recipes);
        userDataAccessObject.save(user);

        LoginInputData inputData = new LoginInputData("fred", "fredtheman", false);
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessViewForLogin(LoginOutputData user) {
                fail("unexpected method call");
            }

            @Override
            public void prepareSuccessViewForSwitch() {
                fail("unexpected method call");
            }

            @Override
            public void prepareFailViewUsername(String error) {
                assertEquals(error, "fred" + ": Account does not exist (Case Sensitive).");

            }

            @Override
            public void prepareFailViewPassword(String error) {
                fail("unexpected method call");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void testWrongPassword() {
        UserProfileFactory userProfileFactory = new CommonUserProfileFactory();

        DataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new DataAccessObject("./userProfiles.csv", new CommonUserProfileFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> dietaryRestriction = new ArrayList<>();
        ArrayList<String> recipes = new ArrayList<>();

        UserProfile user = userProfileFactory.create("Fred", "fredtheman", "male", 111, 111, 11, dietaryRestriction, 0, recipes);
        userDataAccessObject.save(user);

        LoginInputData inputData = new LoginInputData("Fred", "Fredtheman", false);
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessViewForLogin(LoginOutputData user) {
                fail("unexpected method call");
            }

            @Override
            public void prepareSuccessViewForSwitch() {
                fail("unexpected method call");
            }

            @Override
            public void prepareFailViewUsername(String error) {
                fail("unexpected method call");
            }

            @Override
            public void prepareFailViewPassword(String error) {
                assertEquals(error, "Incorrect password for " + "Fred" + ".");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
