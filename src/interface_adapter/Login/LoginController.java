package interface_adapter.Login;

import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String username, String password, boolean switchToSignUp) {
        LoginInputData loginInputData = new LoginInputData(username, password, switchToSignUp);
        loginUseCaseInteractor.execute(loginInputData);
    }
}
