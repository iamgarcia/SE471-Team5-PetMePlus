package com.team5.petmeplus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Dashboard {
    @FXML
    Button myAccountButton;

    @FXML
    Button myPetsButton;

    @FXML
    AnchorPane contentPane;

    @FXML
    public void myAccountOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/myAccount.fxml"));
            AnchorPane accountPane = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(accountPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void myPetsOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/myPets.fxml"));
            AnchorPane petsPane = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(petsPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
