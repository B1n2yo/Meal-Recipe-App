package interface_adapter.Signup;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;

public class BackPresenter {
    private final LoginViewModel loginViewModel;

    private ViewManagerModel viewManagerModel;

    public BackPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView() {
        LoginState loginState = loginViewModel.getState();

        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {

    }
}
