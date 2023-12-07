package use_case;

import interface_adapter.Exercise.ExerciseController;
import interface_adapter.Exercise.ExerciseViewModel;
import use_case.Exercise.ExerciseInputBoundary;
import view.ExerciseView;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
public class ExerciseTest {

    @org.junit.Test
    public void testExerciseView() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        ExerciseInputBoundary sib = null;
        ExerciseController controller = new ExerciseController(sib);
        ExerciseViewModel viewModel = new ExerciseViewModel();
        JPanel exerciseView = new ExerciseView(viewModel, controller);
        JFrame jf = new JFrame();
        jf.setContentPane(exerciseView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel panel = null;
        for (Component component : exerciseView.getComponents()) {
            if (component instanceof LabelTextPanel) {
                panel = (LabelTextPanel) component;
                break;
            }
        }
        JTextField pwdField = (JTextField) panel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        panel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getText()));
//        System.out.println("view-model: " + viewModel.getState().getExerciseName());

        // move to the right in the password field, otherwise the event
        // will type the character as the first character instead of the last!
        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight);

        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        panel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getText()));

        // assert that the values are as expected.
        assertEquals("yz", new String(pwdField.getText()));
    }
}