package interface_adapter.Logout;

import interface_adapter.Signup.SignupState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogoutViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String GENDER_LABEL = "Enter your gender (male or female)";
    public static final String WEIGHT_LABEL = "Enter weight (kg)";
    public static final String HEIGHT_LABEL = "Enter height (cm)";
    public static final String AGE_LABEL = "Enter your age";
    public static final String DIETARY_RESTRICTIONS_LABEL = "Select your dietary restrictions";
    public static final String WEEKLY_BUDGET_LABEL = "Enter your weekly budget";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public LogoutViewModel() {
        super("sign up");
    }

    public void setState(LogoutState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return this.state;
    }
}
