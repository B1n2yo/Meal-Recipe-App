package use_case;

import org.junit.Test;
import use_case.Login.*;

public class LoginTest {

    @Test
    public void testLogin() {
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginInputData loginInputData = new LoginInputData("username", "password");
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                System.out.println("Login successful");
            }
            public void prepareErrorView(LoginOutputData loginOutputData) {
                System.out.println("Login failed");
            }
        };
        loginInteractor.execute(loginInputData);
    }
}
