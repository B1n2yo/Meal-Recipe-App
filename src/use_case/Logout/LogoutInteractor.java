package use_case.Logout;

import interface_adapter.Logout.LogoutPresenter;

public class LogoutInteractor implements LogoutInputBoundary {
    final LogoutOutputBoundary logoutPresenter;
    public LogoutInteractor(LogoutOutputBoundary logoutPresenter) {
        this.logoutPresenter = logoutPresenter;
    }
    public void execute() {
        logoutPresenter.prepareSuccessView();
    }
}