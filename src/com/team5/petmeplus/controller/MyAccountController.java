package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyAccountController implements Initializable {
    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    TextField phoneField;

    @FXML
    TextField addressField;

    @FXML
    Button saveButton;

    @FXML
    public void saveOnAction(ActionEvent event) {
        if (!phoneField.getText().isBlank() || !addressField.getText().isBlank()) {
            Main.owner.setPhone(phoneField.getText());
            Main.owner.setAddress(addressField.getText());
            Main.ownerDao.updateOwner(Main.owner);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameField.setText(Main.owner.getFirstName());
        lastNameField.setText(Main.owner.getLastName());
    }
}
