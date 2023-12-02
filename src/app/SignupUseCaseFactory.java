//package app;
//
//import interface_adapter.Login.LoginViewModel;
//import interface_adapter.Signup.SignupController;
//import interface_adapter.Signup.SignupPresenter;
//import interface_adapter.Signup.SignupViewModel;
//import use_case.Signup.SignupUserDataAccessInterface;
//import entity.CommonUserProfileFactory;
//import entity.UserProfileFactory;
//import interface_adapter.*;
//import use_case.Signup.SignupInputBoundary;
//import use_case.Signup.SignupInteractor;
//import use_case.Signup.SignupOutputBoundary;
//import view.SignupView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class SignupUseCaseFactory {
//
//    /** Prevent instantiation. */
//    private SignupUseCaseFactory() {}
//
//    public static SignupView create(
//            ViewManagerModel viewManagerModel,
//            LoginViewModel loginViewModel,
//            SignupViewModel signupViewModel,
//            SignupUserDataAccessInterface userDataAccessObject) {
//
//        try {
//            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
//            ClearController clearController = createUserClearUseCase(viewManagerModel, clearViewModel, clearUserDataAccessInterface);
//            return new SignupView(signupController, signupViewModel, clearController, clearViewModel);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//
//        return null;
//    }
//
//    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {
//
//        // Notice how we pass this method's parameters to the Presenter.
//        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
//
//        UserFactory userFactory = new CommonUserFactory();
//
//        SignupInputBoundary signupUseCaseInteractor = new SignupInteractor(
//                userDataAccessObject, signupOutputBoundary, userFactory);
//
//        return new SignupController(signupUseCaseInteractor);
//    }
//
//    private static ClearController createUserClearUseCase(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel, ClearUserDataAccessInterface clearUserDataAccessInterface) throws IOException {
//        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(clearViewModel, viewManagerModel);
//
//        ClearInputBoundary clearUseCaseInteractor = new ClearInteractor(clearUserDataAccessInterface, clearOutputBoundary);
//
//        return new ClearController(clearUseCaseInteractor);
//    }
//}
