package org.example.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.example.model.User;
import org.example.services.UserService;

public class LoginWindow {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService;

    public void setService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        try {
            var user = userService.authenticate(username, password);


            if(user.isPresent()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/personal_page_view.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                PersonalWindow controller = loader.getController();
                controller.setServices(userService);
                controller.setUser(username);

                stage.show();
                ((Stage) usernameField.getScene().getWindow()).close();
            }
            else{
                showError("Wrong username or password");
            }

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void handleSignIn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/register_view.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            RegisterWindow controller = loader.getController();
            controller.setService(userService);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}