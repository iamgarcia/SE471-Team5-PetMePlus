import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CreateAccountController {
    @FXML
    private Text accountStatus;
    @FXML
    private TextField firstNameField;
    @FXML
    private Text firstNameError;
    @FXML
    private TextField lastNameField;
    @FXML
    private Text lastNameError;
    @FXML
    private TextField emailField;
    @FXML
    private Text emailError;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text passwordError;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Text confirmPasswordError;
    @FXML
    private Button closeButton;

    @FXML
    public void createAccountOnAction(ActionEvent event) {
        List<Text> errorList = Arrays.asList(firstNameError, lastNameError, emailError, passwordError, confirmPasswordError);

        for (Text error : errorList) {
            error.setVisible(false);
        }

        boolean emptyFirstName = firstNameField.getText().isBlank();
        boolean emptyLastName = lastNameField.getText().isBlank();
        boolean emptyEmail = emailField.getText().isBlank();
        boolean emptyPassword = passwordField.getText().isBlank();
        boolean emptyConfirmPassword = confirmPasswordField.getText().isBlank();

        if (emptyFirstName || emptyLastName || emptyEmail || emptyPassword || emptyConfirmPassword) {
            if (firstNameField.getText().isBlank()) {
                firstNameError.setText("Please enter your first name.");
                firstNameError.setVisible(true);
            }

            if (lastNameField.getText().isBlank()) {
                lastNameError.setText("Please enter your last name.");
                lastNameError.setVisible(true);
            }

            if (emailField.getText().isBlank()) {
                emailError.setText("Please enter your email address.");
                emailError.setVisible(true);
            }

            if (passwordField.getText().isBlank()) {
                passwordError.setText("Please enter your password.");
                passwordError.setVisible(true);
            }

            if (confirmPasswordField.getText().isBlank()) {
                confirmPasswordError.setText("Please confirm your password.");
                confirmPasswordError.setVisible(true);
            }
        } else {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText().toLowerCase(Locale.ROOT);
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!password.equals(confirmPassword)) {
                confirmPasswordError.setText("Those passwords didn't match. Try again.");
                confirmPasswordError.setVisible(true);
            } else {
                DBConnection connectNow = new DBConnection();
                Connection connectDB = connectNow.getConnection();

                try {
                    PreparedStatement statement = connectDB.prepareStatement("INSERT INTO owner(email, password, first_name, last_name) VALUES(?, ?, ?, ?)");
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.setString(3, firstName);
                    statement.setString(4, lastName);
                    statement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    PreparedStatement statement = connectDB.prepareStatement("INSERT INTO signin(email, password) VALUES(?, ?)");
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                accountStatus.setVisible(true);
            }
        }
    }

    @FXML
    public void closeOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
