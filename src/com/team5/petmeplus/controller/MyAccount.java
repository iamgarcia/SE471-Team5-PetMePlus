package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MyAccount {
    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    Button saveButton;

    public boolean isValidFirstName() {
        return !firstNameField.getText().isBlank();
    }

    public boolean isValidLastName() {
        return !lastNameField.getText().isBlank();
    }

    @FXML
    public void saveOnAction() {
        if (isValidFirstName() || isValidLastName()) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();

            Main.owner.setFirstName(firstName);
            Main.owner.setLastName(lastName);

            Main.ownerDao.updateOwner(Main.owner);
        }
    }
}
