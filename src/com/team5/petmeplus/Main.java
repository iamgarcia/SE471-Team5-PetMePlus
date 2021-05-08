package com.team5.petmeplus;

import com.team5.petmeplus.model.Owner;
import com.team5.petmeplus.model.OwnerDao;
import com.team5.petmeplus.model.OwnerDaoImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static OwnerDao ownerDao;
    public static Owner owner;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ownerDao = new OwnerDaoImpl();

        Parent root = FXMLLoader.load(getClass().getResource("view/signIn.fxml"));
        primaryStage.setTitle("PetMe+");
        primaryStage.setScene(new Scene(root, 520, 400));
//        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
