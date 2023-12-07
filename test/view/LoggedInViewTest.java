package view;

import interface_adapter.Logged_in.LoggedInViewModel;
import interface_adapter.WeeklyDiet.WeeklyDietController;
import interface_adapter.Logout.LogoutController;
import use_case.WeeklyDiet.WeeklyDietInputBoundary;
import use_case.Logout.LogoutInputBoundary;
import use_case.WeeklyDiet.WeeklyDietInputData;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoggedInViewTest {

    @org.junit.Test
    public void testLoggedInView() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        WeeklyDietInputBoundary wdib = weeklyDietInputData -> new WeeklyDietInputData("user1", false);
        LogoutInputBoundary lib = null;
        WeeklyDietController weeklyDietController = new WeeklyDietController(wdib);
        LogoutController logoutController = new LogoutController(lib);
        LoggedInViewModel viewModel = new LoggedInViewModel();
        JPanel loggedInView = new LoggedInView(weeklyDietController, viewModel, logoutController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedInView);
        jf.pack();
        jf.setVisible(true);

        JPanel buttons = (JPanel) loggedInView.getComponent(1);
        JButton getRecipes = (JButton) buttons.getComponent(0);

        ActionEvent event = new ActionEvent(
                getRecipes,
                ActionEvent.ACTION_PERFORMED,
                "",
                System.currentTimeMillis(),
                0
        );

        getRecipes.dispatchEvent(event);

        // pause execution for 10 seconds
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(viewModel.getState().getMealPlan());

    }
}