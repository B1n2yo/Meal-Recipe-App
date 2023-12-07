package use_case.Signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailViewUsername(String error);
    void prepareFailViewPassword(String error);
    void prepareFailViewGender(String error);
    void prepareFailViewWeight(String error);
    void prepareFailViewHeight(String error);
    void prepareFailViewAge(String error);

}
