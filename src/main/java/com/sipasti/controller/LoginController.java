package com.sipasti.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{

    private Stage stage;
    private Parent root;

    @FXML
    protected void loginButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

}