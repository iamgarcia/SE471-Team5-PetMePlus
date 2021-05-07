import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorMessageText;

    @FXML
    private Button signInButton;

    @FXML
    private Button createAccountButton;

    @FXML
    public void signInButtonOnAction(ActionEvent event) throws SQLException {
        boolean emptyEmailField = emailField.getText().isBlank();
        boolean emptyPasswordField = passwordField.getText().isBlank();

        errorMessageText.setVisible(false);

        if (emptyEmailField || emptyPasswordField) {
            if (emptyEmailField) {
                errorMessageText.setText("Please enter your email address.");
            } else {
                errorMessageText.setText("Please enter your password.");
            }

            errorMessageText.setVisible(true);
        } else {
            String email = emailField.getText();
            String password = passwordField.getText();

            String dbEmail;
            String dbPassword;

            Connection connectDB = DatabaseConnection.getInstance().getConnection();

            try {
                PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM signin WHERE email=?");
                statement.setString(1, email);

                ResultSet result = statement.executeQuery();

                if (result.next() == false) {
                    errorMessageText.setText("Couldn't find an account associated with this email. Please try again.");
                    errorMessageText.setVisible(true);
                } else {
                    dbPassword = result.getString("password");

                    if (!password.equals(dbPassword)) {
                        errorMessageText.setText("That's not the right password. Try again.");
                        errorMessageText.setVisible(true);
                    } else {
                        // Go to scene 2
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void createAccountOnAction(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
            Stage createAccountStage = new Stage();
            createAccountStage.setScene(new Scene(root, 720, 720));
            createAccountStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
