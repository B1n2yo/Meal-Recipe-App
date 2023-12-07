package interface_adapter.Signup;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Sign Up";
    public static final String USERNAME_LABEL = "Create a username:";
    public static final String PASSWORD_LABEL = "Create a password:";
    public static final String REPEAT_PASSWORD_LABEL = "Re-enter password:";
    public static final String GENDER_LABEL = "Gender (male or female):";
    public static final String WEIGHT_LABEL = "Weight (kg):";
    public static final String HEIGHT_LABEL = "Height (cm):";
    public static final String AGE_LABEL = "Age:";
    public static final String DIETARY_RESTRICTIONS_LABEL = "Select your dietary restrictions";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    public static final String BACK_BUTTON_LABEL = "Back";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
