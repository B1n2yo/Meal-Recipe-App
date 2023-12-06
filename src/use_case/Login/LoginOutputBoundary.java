package use_case.Login;

public interface LoginOutputBoundary {
    void prepareSuccessViewForLogin(LoginOutputData user);
    void prepareFailView(String error);

    void prepareSuccessViewForSwitch();
}
