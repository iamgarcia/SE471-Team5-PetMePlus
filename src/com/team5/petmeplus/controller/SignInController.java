package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SignInController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorMessageText;

    @FXML
    private Button signInButton;

    @FXML
    private Button createAccountButton;

    @FXML
    public void signInButtonOnAction(ActionEvent event) {
        boolean emptyEmailField = emailField.getText().isBlank();
        boolean emptyPasswordField = passwordField.getText().isBlank();

        errorMessageText.setVisible(false);

        if (emptyEmailField || emptyPasswordField) {
            if (emptyEmailField) {
                errorMessageText.setText("Please enter your email address.");
            } else {
                errorMessageText.setText("Please enter your password.");
            }

            errorMessageText.setVisible(true);
        } else {
            String email = emailField.getText();
            String password = passwordField.getText();

            Main.owner = Main.ownerDao.getOwnerByEmailAndPassword(email, password);

            if (Main.owner == null) {
                errorMessageText.setText("Unable to sign in. Please check your email and password and try again.");
                errorMessageText.setVisible(true);
            } else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
                    Main.getStage().setScene(new Scene(root, 900, 600));
                    Main.getStage().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void createAccountOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/createAccount.fxml"));
            Main.getStage().setScene(new Scene(root, 720, 720));
            Main.getStage().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
