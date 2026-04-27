package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.example.services.UserService;

public class RegisterWindow {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService;

    public void setService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void handleCreateAccount() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            userService.createUser(username, password);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/personal_page_view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            PersonalWindow controller = loader.getController();
            controller.setServices(userService);
            controller.setUser(username);

            stage.show();

            ((Stage) usernameField.getScene().getWindow()).close();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
