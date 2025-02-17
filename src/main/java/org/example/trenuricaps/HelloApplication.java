package org.example.trenuricaps;

import javafx.scene.control.Alert;
import org.example.trenuricaps.controller.LoginController;
import org.example.trenuricaps.repo.CityRepository;
import org.example.trenuricaps.repo.TrainStationRepository;
import org.example.trenuricaps.service.Service;
import org.example.trenuricaps.controller.MessageAlert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Service service;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.service = new Service(new CityRepository("jdbc:postgresql://localhost:5432/trenuricaps","postgres","D4t4B4s3!"),
                    new TrainStationRepository("jdbc:postgresql://localhost:5432/trenuricaps","postgres","D4t4B4s3!"));

            primaryStage.setTitle("Welcome!");
            startView(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            MessageAlert.showMessage(primaryStage, Alert.AlertType.ERROR, "Error", "Failed to load the app view.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startView(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/trenuricaps/loginView.fxml"));
        AnchorPane Layout = fxmlLoader.load();
        stage.setScene(new Scene(Layout));

        LoginController startController = fxmlLoader.getController();
        startController.setService(service);
    }
}