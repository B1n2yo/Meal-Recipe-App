package view;

import entity.MealInfo;
import interface_adapter.Login.LoginState;
import interface_adapter.Logout.LogoutController;
import interface_adapter.WeeklyDietController;
import interface_adapter.Logged_in.LoggedInState;
import interface_adapter.Logged_in.LoggedInViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final LoggedInViewModel loggedInViewModel;
    private final WeeklyDietController weeklyDietController;
    private final LogoutController logoutController;

    private final JButton getRecipes;
    private final JButton logOut;
    private JDialog recipes;
    private final Border border = BorderFactory.createLineBorder(new java.awt.Color(136, 240, 115), 5);
    private final Border invisBorder = BorderFactory.createLineBorder(new java.awt.Color(23, 32, 46), 10);
    private final java.awt.Color fontColour = new java.awt.Color(222, 247, 250);
    private final Dictionary<Integer, String> mealType = new Hashtable<>() {{
        put(0, "Breakfast");
        put(1, "Lunch");
        put(2, "Dinner");
    }};

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
                            JScrollPane scrollPane = new JScrollPane();
                            JPanel recipe = new JPanel();
                            recipe.setLayout(new BoxLayout(recipe, BoxLayout.Y_AXIS));
                            ArrayList<MealInfo> weeklyDiet = currentState.getMealPlan();
                            for (int j = 0; j < weeklyDiet.size() / 3; j++) {
                                JPanel day = new JPanel();
                                day.setLayout(new GridLayout(4, 1, 5, 10));
                                day.setBackground(new java.awt.Color(23, 32, 46));
                                JLabel dayNum = new JLabel("Day " + (j + 1));
                                dayNum.setForeground(fontColour);
                                dayNum.setAlignmentX(Component.CENTER_ALIGNMENT);
                                day.add(dayNum);
                                for (int i = 0; i < 3; i++) {
                                    MealInfo diet = weeklyDiet.get(i);
                                    JPanel meal = new JPanel();
                                    meal.setLayout(new GridLayout(3, 1, 5, 5));
                                    meal.setBackground(new java.awt.Color(23, 32, 46));
                                    JLabel recipeName = new JLabel(mealType.get(i) + ": " + diet.getName());
                                    recipeName.setForeground(fontColour);
                                    JLabel recipeDescription = new JLabel(diet.getDescription());
                                    recipeDescription.setForeground(new java.awt.Color(136, 240, 115));
                                    recipeDescription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    recipeDescription.addMouseListener(new MouseAdapter() {

                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            // the user clicks on the label
                                            try {

                                                Desktop.getDesktop().browse(new URI(diet.getDescription()));

                                            } catch (IOException | URISyntaxException e1) {
                                                e1.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void mouseEntered(MouseEvent e) {
                                            // the mouse has entered the label
                                        }

                                        @Override
                                        public void mouseExited(MouseEvent e) {
                                            // the mouse has exited the label
                                        }
                                    });
                                    JLabel recipeCals = new JLabel("Calories: " + diet.getCalories());
                                    recipeCals.setForeground(fontColour);
                                    meal.add(recipeName);
                                    meal.add(recipeDescription);
                                    meal.add(recipeCals);
                                    day.add(meal);
                                }
                                day.setBorder(new CompoundBorder(border, invisBorder));
                                day.setAlignmentY(Component.CENTER_ALIGNMENT);
                                recipe.add(day);
                            }

                            recipes.setSize(600, 600);
                            recipes.getContentPane().setLayout(new BorderLayout(0, 0));
                            recipes.getContentPane().add(scrollPane, BorderLayout.CENTER);
                            scrollPane.setViewportView(recipe);
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