package com.team5.petmeplus;

import com.team5.petmeplus.model.*;
import com.team5.petmeplus.util.DatabaseConnectionPool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static OwnerDao ownerDao;
    public static Owner owner;
    public static PetDao petDao;
    public static List<Pet> pets = new ArrayList<>();
    private static Stage currentStage;
    public static Stage getStage() {
        return currentStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        currentStage = primaryStage;
        ownerDao = new OwnerDaoImpl();
        petDao = new PetDaoImpl();

        Parent root = FXMLLoader.load(getClass().getResource("view/signIn.fxml"));
        primaryStage.setTitle("PetMe+");
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }
}
