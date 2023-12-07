package use_case.Login;

public interface LoginOutputBoundary {
    void prepareSuccessViewForLogin(LoginOutputData user);

    void prepareSuccessViewForSwitch();
    void prepareFailViewUsername(String error);
    void prepareFailViewPassword(String error);
}
