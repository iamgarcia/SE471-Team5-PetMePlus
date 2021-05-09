package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import com.team5.petmeplus.model.Owner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class CreateAccountController {
    @FXML
    private Text accountStatus;

    @FXML
    private TextField firstNameField;

    @FXML
    private Text firstNameError;

    @FXML
    private TextField lastNameField;

    @FXML
    private Text lastNameError;

    @FXML
    private TextField emailField;

    @FXML
    private Text emailError;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text passwordError;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Text confirmPasswordError;

    @FXML
    private Button closeButton;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    private boolean isValidFirstName() {
        if (firstNameField.getText().isBlank()) {
            firstNameError.setText("Please enter your first name.");
            firstNameError.setVisible(true);
            return false;
        }

        firstName = firstNameField.getText();
        return true;
    }

    private boolean isValidLastName() {
        if (lastNameField.getText().isBlank()) {
            lastNameError.setText("Please enter your last name.");
            lastNameError.setVisible(true);
            return false;
        }

        lastName = lastNameField.getText();
        return true;
    }

    private boolean isValidEmail() {
        if (emailField.getText().isBlank()) {
            emailError.setText("Please enter your email address.");
            emailError.setVisible(true);
            return false;
        }

        email = emailField.getText().toLowerCase();
        return true;
    }

    private boolean isValidPassword() {
        if (passwordField.getText().isBlank()) {
            passwordError.setText("Please enter your password.");
            passwordError.setVisible(true);
            return false;
        }

        password = passwordField.getText();
        return true;
    }

    private boolean isValidConfirmPassword() {
        if (confirmPasswordField.getText().isBlank()) {
            confirmPasswordError.setText("Please confirm your password.");
            confirmPasswordError.setVisible(true);
            return false;
        }

        confirmPassword = confirmPasswordField.getText();
        return true;
    }

    @FXML
    public void createAccountOnAction(ActionEvent event) {
        List<Text> errorList = Arrays.asList(firstNameError, lastNameError, emailError, passwordError, confirmPasswordError);

        for (Text error : errorList) {
            error.setVisible(false);
        }

        if (isValidFirstName() & isValidLastName() & isValidEmail() & isValidPassword() & isValidConfirmPassword()) {
            if (!password.equals(confirmPassword)) {
                confirmPasswordError.setText("Those passwords didn't match. Try again.");
                confirmPasswordError.setVisible(true);
            } else {
                Owner owner = new Owner(firstName, lastName, email, password);

                if (Main.ownerDao.insertOwner(owner)) {
                    accountStatus.setText("Account created.");
                } else {
                    accountStatus.setText("Unable to create account.");
                }

                accountStatus.setVisible(true);
            }
        }
    }

    @FXML
    public void closeOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/signIn.fxml"));
            Main.getStage().setScene(new Scene(root, 520, 400));
            Main.getStage().show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
