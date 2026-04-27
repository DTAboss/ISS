package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.controller.LoginWindow;
import org.example.model.User;
import org.example.repository.UserDBRepository;
import org.example.repository.UserRepository;
import org.example.services.UserService;

public class StartApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String url = "jdbc:postgresql://localhost:5432/traveljurnal";
        String username = "postgres";
        String password = "denis2004";

        UserRepository userRepository = new UserDBRepository(url, username, password);
        UserService userService = new UserService(userRepository);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login_view.fxml"));
        Scene scene = new Scene(loader.load());

        LoginWindow controller = loader.getController();
        controller.setService(userService);

        stage.setTitle("Trip Journal");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
