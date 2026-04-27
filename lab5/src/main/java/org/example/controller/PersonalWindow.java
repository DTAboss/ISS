package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.example.services.UserService;

public class PersonalWindow {

    @FXML
    private Label welcomeLabel;

    private UserService userService;
    private String currentUser;

    public void setServices(UserService userService) {
        this.userService = userService;
    }

    public void setUser(String username) {
        this.currentUser = username;
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    @FXML
    public void handleLogout() {
        //userService.logout(currentUser);

        ((Stage) welcomeLabel.getScene().getWindow()).close();
    }
}
