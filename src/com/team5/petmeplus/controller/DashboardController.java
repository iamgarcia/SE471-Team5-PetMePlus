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
            AnchorPane myAccountPane = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(myAccountPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void myPetsOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/myPets.fxml"));
            AnchorPane myPetsPane = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(myPetsPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addPetsOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/addPets.fxml"));
            AnchorPane addPetsPane = fxmlLoader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(addPetsPane);
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
            Main.pets = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
