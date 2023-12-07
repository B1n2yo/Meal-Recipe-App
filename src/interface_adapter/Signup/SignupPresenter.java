package interface_adapter.Signup;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        LoginState loginState = loginViewModel.getState();

        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailViewUsername(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
    public void prepareFailViewPassword(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setPasswordError(error);
        signupViewModel.firePropertyChanged();
    }
    public void prepareFailViewGender(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setGenderError(error);
        signupViewModel.firePropertyChanged();
    }
    public void prepareFailViewWeight(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setWeightError(error);
        signupViewModel.firePropertyChanged();
    }
    public void prepareFailViewHeight(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setHeightError(error);
        signupViewModel.firePropertyChanged();
    }
    public void prepareFailViewAge(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setAgeError(error);
        signupViewModel.firePropertyChanged();
    }
}

