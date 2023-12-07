package view;

import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;

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

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField genderInputField = new JTextField(15);
    private final JTextField weightInputField = new JTextField(15);
    private final JTextField heightInputField = new JTextField(15);
    private final JTextField ageInputField = new JTextField(15);
    private final JButton dietaryRestrictionButton;

    private final SignupController signupController;

    private final JButton signUp;
    
    // Colours
    private final java.awt.Color FONT_COLOUR = new java.awt.Color(222, 247, 250);
    private final java.awt.Color BACKGROUND_COLOUR = new java.awt.Color(23, 32, 46);
    private final java.awt.Color ACCENT_COLOUR = new java.awt.Color(136, 240, 115);
    private final Border BORDER = BorderFactory.createLineBorder(ACCENT_COLOUR, 5);
    private final Border INVIS_BORDER = BorderFactory.createLineBorder(
            new java.awt.Color(23, 32, 46), 10);

    public SignupView(SignupController controller, SignupViewModel signupViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setForeground(FONT_COLOUR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
        usernameInfo.setBackground(BACKGROUND_COLOUR);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        passwordInfo.setBackground(BACKGROUND_COLOUR);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        repeatPasswordInfo.setBackground(BACKGROUND_COLOUR);
        LabelTextPanel genderInfo = new LabelTextPanel(
                new JLabel(signupViewModel.GENDER_LABEL), genderInputField);
        genderInfo.setBackground(BACKGROUND_COLOUR);

        LabelTextPanel weightInfo = new LabelTextPanel(
                new JLabel(signupViewModel.WEIGHT_LABEL), weightInputField);
        weightInfo.setBackground(BACKGROUND_COLOUR);
        LabelTextPanel heightInfo = new LabelTextPanel(
                new JLabel(signupViewModel.HEIGHT_LABEL), heightInputField);
        heightInfo.setBackground(BACKGROUND_COLOUR);
        LabelTextPanel ageInfo = new LabelTextPanel(
                new JLabel(signupViewModel.AGE_LABEL), ageInputField);
        ageInfo.setBackground(BACKGROUND_COLOUR);
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setForeground(FONT_COLOUR);
        signUp.setBackground(BACKGROUND_COLOUR);

        dietaryRestrictionButton = new JButton(signupViewModel.DIETARY_RESTRICTIONS_LABEL);
        dietaryRestrictionButton.setForeground(FONT_COLOUR);
        dietaryRestrictionButton.setBackground(BACKGROUND_COLOUR);

        JPopupMenu popUpMenu = new JPopupMenu();
        popUpMenu.setBackground(BACKGROUND_COLOUR);

        JCheckBox dairyFree = new JCheckBox("dairy-free");
        dairyFree.setForeground(FONT_COLOUR);
        dairyFree.setBackground(BACKGROUND_COLOUR);
        JCheckBox glutenFree = new JCheckBox("gluten-free");
        glutenFree.setForeground(FONT_COLOUR);
        glutenFree.setBackground(BACKGROUND_COLOUR);
        JCheckBox peanutFree = new JCheckBox("peanut-free");
        peanutFree.setForeground(FONT_COLOUR);
        peanutFree.setBackground(BACKGROUND_COLOUR);
        JCheckBox vegetarian = new JCheckBox("vegetarian");
        vegetarian.setForeground(FONT_COLOUR);
        vegetarian.setBackground(BACKGROUND_COLOUR);

        popUpMenu.add(dairyFree);
        popUpMenu.add(glutenFree);
        popUpMenu.add(peanutFree);
        popUpMenu.add(vegetarian);

        dietaryRestrictionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpMenu.show(dietaryRestrictionButton, 0, dietaryRestrictionButton.getHeight());
            }
        });

        dairyFree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dairyFree.isSelected()) {
                    SignupState currentState = signupViewModel.getState();
                    currentState.addRestriction(dairyFree.getText());
                }
                else {
                    SignupState currentState = signupViewModel.getState();
                    currentState.removeRestriction(dairyFree.getText());
                }
            }
        });

        glutenFree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (glutenFree.isSelected()) {
                    SignupState currentState = signupViewModel.getState();
                    currentState.addRestriction(glutenFree.getText());
                }
                else {
                    SignupState currentState = signupViewModel.getState();
                    currentState.removeRestriction(glutenFree.getText());
                }
            }
        });

        peanutFree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (peanutFree.isSelected()) {
                    SignupState currentState = signupViewModel.getState();
                    currentState.addRestriction(peanutFree.getText());
                }
                else {
                    SignupState currentState = signupViewModel.getState();
                    currentState.removeRestriction(peanutFree.getText());
                }
            }
        });

        vegetarian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vegetarian.isSelected()) {
                    SignupState currentState = signupViewModel.getState();
                    currentState.addRestriction(vegetarian.getText());
                }
                else {
                    SignupState currentState = signupViewModel.getState();
                    currentState.removeRestriction(vegetarian.getText());
                }
            }
        });

//        String selectedRestriction = (String) dropDownMenu.getSelectedItem();
//        SignupState currentState = signupViewModel.getState();
//        currentState.addRestriction(selectedRestriction);
        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getGender(),
                                    currentState.getWeight(),
                                    currentState.getHeight(),
                                    currentState.getAge(),
                                    currentState.getDietaryRestrictions(),
                                    currentState.getRecommendedDailyCalories(),
                                    currentState.getRecipes());
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(String.valueOf(repeatPasswordInputField.getPassword()) + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        genderInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setGender(genderInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        weightInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setWeight(Float.valueOf(weightInputField.getText() + e.getKeyChar()));
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        heightInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setHeight(Float.valueOf(heightInputField.getText() + e.getKeyChar()));
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        ageInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setAge(Integer.valueOf(ageInputField.getText() + e.getKeyChar()));
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new GridLayout(10, 1));
        this.setBackground(BACKGROUND_COLOUR);
        this.setBorder(new CompoundBorder(BORDER, INVIS_BORDER));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(genderInfo);
        this.add(weightInfo);
        this.add(heightInfo);
        this.add(ageInfo);
        this.add(dietaryRestrictionButton);
        this.add(signUp);

    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
            state.setUsernameError(null);
        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
            state.setPasswordError(null);
        } else if (state.getGenderError() != null) {
            JOptionPane.showMessageDialog(this, state.getGenderError());
            state.setGenderError(null);
        } else if (state.getWeightError() != null) {
            JOptionPane.showMessageDialog(this, state.getWeightError());
            state.setWeightError(null);
        } else if (state.getHeightError() != null) {
            JOptionPane.showMessageDialog(this, state.getHeightError());
            state.setHeightError(null);
        } else if (state.getAgeError() != null) {
            JOptionPane.showMessageDialog(this, state.getAgeError());
            state.setAgeError(null);
        }
    }


}