package controller;

import backend.SheetsQuickstart;
import backend.Surat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void loginButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/table.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
//        scene = new Scene(root);
//        stage.setMaximized(true);
//        stage.setScene(scene);
//        stage.show();
    }

}