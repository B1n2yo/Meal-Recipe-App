package interface_adapter.Login;

import interface_adapter.Logged_in.LoggedInState;
import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary  {
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    public void prepareSuccessViewForSwitch() {
        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareSuccessViewForLogin(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailViewUsername(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    public void prepareFailViewPassword(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setPasswordError(error);
        loginViewModel.firePropertyChanged();
    }
}
