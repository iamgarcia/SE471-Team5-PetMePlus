package com.team5.petmeplus.controller;

import com.team5.petmeplus.Main;
import com.team5.petmeplus.model.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddPetsController implements Initializable {
    private final String[] species = {"Dog", "Cat", "Hamster", "Bird"};
    @FXML
    private Text petStatus;
    @FXML
    private TextField nameField;
    @FXML
    private Text nameError;
    @FXML
    private ChoiceBox<String> specieChoiceBox;
    @FXML
    private Text specieError;

    @FXML
    private TextField breedField;

    @FXML
    private Text breedError;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Text birthDateError;

    private String name;
    private String specie;
    private String breed;
    private LocalDate localDate;
    private Date birthDate;

    private boolean isValidName() {
        if (nameField.getText().isBlank()) {
            nameError.setVisible(true);
            return false;
        }

        name = nameField.getText();
        return true;
    }

    private boolean isValidSpecie() {
        if (specieChoiceBox.getValue() == null) {
            specieError.setVisible(true);
            return false;
        }

        specie = specieChoiceBox.getValue();
        return true;
    }

    private boolean isValidBreed() {
        if (breedField.getText().isBlank()) {
            breedError.setVisible(true);
            return false;
        }

        breed = breedField.getText();
        return true;
    }

    private boolean isValidBirthDate() {
        if (birthDatePicker.getValue() == null) {
            birthDateError.setVisible(true);
            return false;
        }

        localDate = birthDatePicker.getValue();
        birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return true;
    }

    @FXML
    public void addPetOnAction(ActionEvent event) {
        List<Text> errorList = Arrays.asList(nameError, specieError, breedError, birthDateError);

        for (Text error : errorList) {
            error.setVisible(false);
        }

        if (isValidName() & isValidSpecie() & isValidBreed() & isValidBirthDate()) {
            Pet pet = new Pet(name, specie, breed, birthDate);
            Main.pets.add(pet);
            if (Main.petDao.insertPet(pet)) {
                petStatus.setText("Pet added.");
            } else {
                petStatus.setText("Unable to add pet.");
            }

            petStatus.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specieChoiceBox.getItems().addAll(species);
    }
}
