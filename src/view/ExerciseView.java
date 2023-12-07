package view;

import interface_adapter.Exercise.ExerciseController;
import interface_adapter.Exercise.ExerciseState;
import interface_adapter.Exercise.ExerciseViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ExerciseView  extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "exercise";
    private final ExerciseViewModel exerciseViewModel;
    private final ExerciseController exerciseController;
    final JTextField exerciseInputField = new JTextField(15);
    final JButton calculateExercise;

    public ExerciseView(ExerciseViewModel exerciseViewModel, ExerciseController exerciseController) {
        this.exerciseViewModel = exerciseViewModel;
        this.exerciseViewModel.addPropertyChangeListener(this);
        this.exerciseController = exerciseController;

        JLabel title = new JLabel("exercise Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel exerciseInfo = new LabelTextPanel(
                new JLabel("Enter your exercise: (Type of Exercise performed), time in minutes.)"),
                exerciseInputField);

        JPanel buttons = new JPanel();
        calculateExercise = new JButton(exerciseViewModel.CALCULATE_EXERCISE_LABEL);
        buttons.add(calculateExercise);

        calculateExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(calculateExercise)) {
                    ExerciseState currentState = exerciseViewModel.getState();
                    String username = currentState.getUsername();
                    String exercise = currentState.getExerciseName();

                    exerciseController.execute(username, exercise);
                }
            }
        });
        exerciseInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ExerciseState currentState = exerciseViewModel.getState();
                currentState.setExerciseName(exerciseInputField.getText() + e.getKeyChar());
                exerciseViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(title);
        this.add(exerciseInfo);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExerciseState exerciseState = (ExerciseState) evt.getNewValue();
        if (exerciseState.getSuccessMessage() != null) {
            JOptionPane.showMessageDialog(this, exerciseState.getSuccessMessage());
            exerciseState.setSuccessMessage(null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
