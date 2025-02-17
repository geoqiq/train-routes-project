package org.example.trenuricaps.controller;

import org.example.trenuricaps.HelloApplication;
import org.example.trenuricaps.service.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    private Service service;
    public void setService(Service service) {
        this.service = service;
    }

    public void handleLogin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/trenuricaps/clientView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Buy train tickets!");
            stage.setScene(new Scene(root));
            ClientController clientCtrl = fxmlLoader.getController();
            clientCtrl.setService(service);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
