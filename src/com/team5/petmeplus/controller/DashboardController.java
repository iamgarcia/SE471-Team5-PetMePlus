package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DashboardController {
    @FXML
    Button myAccountButton;

    @FXML
    Button myPetsButton;

    @FXML
    Button signOutButton;

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

    @FXML
    public void signOutOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/signIn.fxml"));
            Main.getStage().setScene(new Scene(root, 520, 400));
            Main.getStage().show();
            Main.owner = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
