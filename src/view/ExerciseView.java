package view;

import interface_adapter.Exercise.ExerciseController;
import interface_adapter.Exercise.ExerciseState;
import interface_adapter.Exercise.ExerciseViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
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

    // Colours
    private final java.awt.Color FONT_COLOUR = new java.awt.Color(222, 247, 250);
    private final java.awt.Color BACKGROUND_COLOUR = new java.awt.Color(23, 32, 46);
    private final java.awt.Color ACCENT_COLOUR = new java.awt.Color(136, 240, 115);
    private final Border BORDER = BorderFactory.createLineBorder(ACCENT_COLOUR, 5);
    private final Border INVIS_BORDER = BorderFactory.createLineBorder(
            new java.awt.Color(23, 32, 46),10);

    public ExerciseView(ExerciseViewModel exerciseViewModel, ExerciseController exerciseController) {
        this.exerciseViewModel = exerciseViewModel;
        this.exerciseViewModel.addPropertyChangeListener(this);
        this.exerciseController = exerciseController;

        JLabel title = new JLabel("Exercise screen");
        title.setForeground(FONT_COLOUR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel exerciseInfo = new LabelTextPanel(
                new JLabel("Enter your exercise: (type of exercise performed, time in minutes)"),
                exerciseInputField);
        exerciseInfo.setForeground(FONT_COLOUR);
        exerciseInfo.setBackground(BACKGROUND_COLOUR);

        JPanel buttons = new JPanel();
        buttons.setBackground(BACKGROUND_COLOUR);
        calculateExercise = new JButton(exerciseViewModel.CALCULATE_EXERCISE_LABEL);
        calculateExercise.setForeground(FONT_COLOUR);
        calculateExercise.setBackground(BACKGROUND_COLOUR);
        buttons.add(calculateExercise);

        calculateExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(calculateExercise)) {
                    ExerciseState currentState = exerciseViewModel.getState();
                    String username = currentState.getUsername();
                    String exercise = currentState.getExerciseName();
                    exerciseController.execute(username, exercise);
                    ExerciseState newState = new ExerciseState();
                    exerciseViewModel.setState(newState);
                    exerciseInputField.setText("");
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

        this.setLayout(new GridLayout(3, 1));
        this.setBackground(BACKGROUND_COLOUR);
        this.setBorder(new CompoundBorder(BORDER, INVIS_BORDER));

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
