package com.sipasti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainFrame extends Application {

    @Override
    public void start(Stage stage) throws IOException, GeneralSecurityException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);
        //stage.setResizable(false);

        Image icon = new Image(MainFrame.class.getResourceAsStream("/img/uin.png"));
        stage.getIcons().add(icon);

        stage.setTitle("SIPASTI");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}