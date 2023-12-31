package use_case.Login;

import entity.UserProfile;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject,
                           LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        boolean switchToSignUp = loginInputData.getSwitchToSignUp();
        if (switchToSignUp) {
            loginPresenter.prepareSuccessViewForSwitch();
        } else {
            if (!userDataAccessObject.existsByName(username)) {
                loginPresenter.prepareFailViewUsername(username + ": Account does not exist (Case Sensitive).");
            } else {
                String pwd = userDataAccessObject.getUserProfile(username).getPassword();
                if (!password.equals(pwd)) {
                    loginPresenter.prepareFailViewPassword("Incorrect password for " + username + ".");
                } else {
                    UserProfile user = userDataAccessObject.getUserProfile(username);

                    LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                    loginPresenter.prepareSuccessViewForLogin(loginOutputData);
                }
            }
        }
    }
}
