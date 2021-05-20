package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import com.team5.petmeplus.api.Business;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServicesController implements Initializable {
    private final String[] services = {"Vet", "Emergency Vet", "Grooming", "Pet Store"};

    @FXML
    ChoiceBox<String> serviceChoiceBox;

    @FXML
    TextField zipcodeField;

    @FXML
    Button searchButton;

    @FXML
    ListView<String> businessResults;

    private String service;
    private String zipcode;

    private boolean isValidService() {
        if (serviceChoiceBox.getValue() != null) {
            service = serviceChoiceBox.getValue().toLowerCase();
            service = service.replaceAll(" ", "%20");

            return true;
        }

        return false;
    }

    private boolean isValidZipcode() {
        if (!zipcodeField.getText().isBlank()) {
            zipcode = zipcodeField.getText();

            String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(zipcode);

            return matcher.matches();
        }

        return false;
    }

    @FXML
    public void searchOnAction(ActionEvent event) {
        if (isValidService() & isValidZipcode()) {
            Main.businesses = Main.yelpApi.searchForBusinessesByZipcode(service, zipcode);

            for (Business business : Main.businesses) {
                businessResults.getItems().add("Name: " + business.getName() + "\n" +
                        "Phone: " + business.getPhone() + "\n" +
                        "Address: " + business.getLocation().getFullAddress());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serviceChoiceBox.getItems().addAll(services);
    }
}
