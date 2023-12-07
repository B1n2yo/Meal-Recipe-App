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

public class ExerciseView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "exercise";
    private final ExerciseViewModel exerciseViewModel;

    private final JTextField exerciseInputField = new JTextField(15);

    private final ExerciseController exerciseController;

    final JButton calculateExercise;

    /**
     * A window with a title and a JButton.
     */
    public ExerciseView(ExerciseViewModel exerciseViewModel, ExerciseController exerciseController) {
        this.exerciseViewModel = exerciseViewModel;
        this.exerciseController = exerciseController;
        this.exerciseViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Exercise calories calculator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel exerciseInfo = new LabelTextPanel(new JLabel("exercsie info"), exerciseInputField);

        JPanel buttons = new JPanel();
        calculateExercise = new JButton(exerciseViewModel.CALCULATE_EXERCISE_LABEL);
        buttons.add(calculateExercise);

        calculateExercise.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(calculateExercise)) {
                            ExerciseState currentState = exerciseViewModel.getState();
                            System.out.println(currentState.getUsername());
                            System.out.println(currentState.getExerciseName());

                            exerciseController.execute(currentState.getUsername(), currentState.getExerciseName()
                            );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(exerciseInfo);
        this.add(buttons);

        exerciseInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ExerciseState currentState = exerciseViewModel.getState();
                        String text = exerciseInputField.getText() + e.getKeyChar();
                        currentState.setExerciseName(text);
                        exerciseViewModel.setState(currentState);
//                        System.out.println(exerciseViewModel.getState().getExerciseName());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExerciseState state = (ExerciseState) evt.getNewValue();
    }
}
