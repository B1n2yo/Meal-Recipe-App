package view;

import entity.MealInfo;
import interface_adapter.Login.LoginState;
import interface_adapter.Logout.LogoutController;
import interface_adapter.WeeklyDietController;
import interface_adapter.Logged_in.LoggedInState;
import interface_adapter.Logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;
    private final WeeklyDietController weeklyDietController;
    private final LogoutController logoutController;

    private final JButton getRecipes;
    private final JButton logOut;
    private JDialog recipes;

    public LoggedInView(WeeklyDietController controller, LoggedInViewModel loggedInViewModel,
                        LogoutController logoutController) {

        this.weeklyDietController = controller;
        this.loggedInViewModel = loggedInViewModel;
        loggedInViewModel.addPropertyChangeListener(this);
        this.logoutController = logoutController;

        JLabel title = new JLabel(loggedInViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        getRecipes = new JButton(loggedInViewModel.GET_RECIPES_BUTTON_LABEL);
        buttons.add(getRecipes);
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        getRecipes.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getRecipes)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            weeklyDietController.execute(currentState.getUsername());

                            recipes = new JDialog();
                            ArrayList<MealInfo> weeklyDiet = currentState.getMealPlan();
                            for (int i = 0; i < weeklyDiet.size(); i++) {
                                JLabel recipe = new JLabel(weeklyDiet.get(i).toString());
                                recipes.add(recipe);
                            }
                            recipes.setVisible(true);
                        }
                    }
                }
        );
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            logoutController.execute();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState loggedInstate = (LoggedInState) evt.getNewValue();
    }
}