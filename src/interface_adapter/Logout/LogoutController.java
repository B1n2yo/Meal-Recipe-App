package interface_adapter.Logout;

import use_case.Logout.LogoutInputBoundary;

public class LogoutController {
    final LogoutInputBoundary logoutUseCaseInteractor;
    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }
    public void execute() {
        logoutUseCaseInteractor.execute();
    }

}
