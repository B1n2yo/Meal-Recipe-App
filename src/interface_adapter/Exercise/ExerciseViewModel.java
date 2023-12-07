package interface_adapter.Exercise;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ExerciseViewModel extends ViewModel {

    public static final String CALCULATE_EXERCISE_LABEL = "Calculate Exercise";

    private ExerciseState state = new ExerciseState();

    public ExerciseViewModel() {
        super("exercise");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ExerciseState getState() {
        return state;
    }

    public void setState(ExerciseState state) {
        this.state = state;
    }
}
